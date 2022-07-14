package com.ddf.group.purchase.core.application.impl;

import cn.hutool.core.collection.CollUtil;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.model.PageResult;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.boot.common.core.util.PageUtil;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.api.request.group.CustomizeCreateRequest;
import com.ddf.group.purchase.api.request.group.ModifyGroupRequest;
import com.ddf.group.purchase.api.request.group.MyInitiatedGroupPageRequest;
import com.ddf.group.purchase.api.request.group.MyJoinGroupPageRequest;
import com.ddf.group.purchase.api.request.group.UpdateGroupStatusRequest;
import com.ddf.group.purchase.api.response.group.MyInitiatedGroupPageResponse;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import com.ddf.group.purchase.core.client.UserClient;
import com.ddf.group.purchase.core.converter.GroupPurchaseInfoConvert;
import com.ddf.group.purchase.core.exception.ExceptionCode;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import com.ddf.group.purchase.core.model.entity.UserJoinGroupInfo;
import com.ddf.group.purchase.core.repository.GroupPurchaseInfoRepository;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>团购业务层</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 23:17
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class GroupPurchaseApplicationServiceImpl implements GroupPurchaseApplicationService {

    private final UserClient userClient;
    private final UserInfoRepository userInfoRepository;
    private final GroupPurchaseInfoRepository groupPurchaseInfoRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFromWxJieLong(CreateFromWxJieLongRequest request) {
        final UserInfo userInfo = userClient.currentUserInfo();
        // 解析出来的数据
        final CreateFromWxJieLongRequest.Data data = request.getData();
        // 接龙内容
        final List<CreateFromWxJieLongRequest.UserData> userDataList = data.getUserDataList();
        // 接龙发起人
        final CreateFromWxJieLongRequest.UserData groupMasterUser = userDataList.get(0);
        if (!Objects.equals(userInfo.getBuildingNo(),  groupMasterUser.getBuildingNo())
                || !Objects.equals(userInfo.getRoomNo(),  groupMasterUser.getRoomNo())) {
            throw new BusinessException(ExceptionCode.GROUP_MASTER_NOT_MAPPING);
        }

        final Long currentTimeSeconds = DateUtils.currentTimeSeconds();
        final GroupPurchaseInfo groupPurchaseInfo = new GroupPurchaseInfo();
        groupPurchaseInfo.setName(data.getName());
        groupPurchaseInfo.setGroupMasterUid(userInfo.getId());
        groupPurchaseInfo.setStatus(GroupPurchaseStatusEnum.CREATED.getValue());
        groupPurchaseInfo.setRemark(data.getExample() + "\n" + data.getRemarks());
        groupPurchaseInfo.setCtime(currentTimeSeconds);
        groupPurchaseInfo.setMtime(currentTimeSeconds);
        groupPurchaseInfoRepository.insertGroupPurchaseInfo(groupPurchaseInfo);

        List<UserJoinGroupInfo> joins = new ArrayList<>();
        // 将信息自动绑定给用户的参团记录
        for (CreateFromWxJieLongRequest.UserData userData : userDataList) {
            // 跳过团长和信息不全的用户
            if (userData == groupMasterUser || StringUtils.isAnyBlank(userData.getBuildingNo(), userData.getRoomNo())) {
                continue;
            }
            // 根据楼栋号和房间号查找参团人，默认只能是一个小区
            final List<UserInfo> list = userInfoRepository.getByBuildingAndRoomNo(
                    userInfo.getCommunityId(), userData.getBuildingNo(), userData.getRoomNo());
            if (CollUtil.isEmpty(list)) {
                continue;
            }
            for (UserInfo user : list) {
                final UserJoinGroupInfo joinGroupInfo = new UserJoinGroupInfo();
                joinGroupInfo.setGroupPurchaseId(groupPurchaseInfo.getId());
                joinGroupInfo.setJoinUid(user.getId());
                joinGroupInfo.setCtime(currentTimeSeconds);
                joinGroupInfo.setSubscribeProgress(Boolean.TRUE);
                joins.add(joinGroupInfo);
            }
        }
        if (CollUtil.isNotEmpty(joins)) {
            groupPurchaseInfoRepository.batchInsertUserJoinGroup(joins);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void customizeCreate(CustomizeCreateRequest request) {
        final Long currentTimeSeconds = DateUtils.currentTimeSeconds();
        final GroupPurchaseInfo groupPurchaseInfo = new GroupPurchaseInfo();
        groupPurchaseInfo.setName(request.getName());
        groupPurchaseInfo.setGroupMasterUid(UserContextUtil.getLongUserId());
        groupPurchaseInfo.setStatus(GroupPurchaseStatusEnum.CREATED.getValue());
        groupPurchaseInfo.setRemark(request.getRemark());
        groupPurchaseInfo.setCtime(currentTimeSeconds);
        groupPurchaseInfo.setMtime(currentTimeSeconds);
        groupPurchaseInfoRepository.insertGroupPurchaseInfo(groupPurchaseInfo);
    }

    @Override
    public void modifyGroupInfo(ModifyGroupRequest request) {
        final GroupPurchaseInfo groupPurchaseInfo = groupPurchaseInfoRepository.selectGroupPurchaseInfoById(request.getId());
        PreconditionUtil.checkArgument(Objects.nonNull(groupPurchaseInfo), ExceptionCode.RECORD_NOT_EXIST);
        PreconditionUtil.checkArgument(Objects.equals(groupPurchaseInfo.getGroupMasterUid(), UserContextUtil.getLongUserId()),
                ExceptionCode.RECORD_NOT_EXIST);
        groupPurchaseInfoRepository.modifyGroupBaseInfo(request.getId(), request.getGroupName(), request.getRemark());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGroupStatus(UpdateGroupStatusRequest request) {
        switch (request.getStatus()) {
            case ARRIVED:
                return groupPurchaseInfoRepository.updateStatusToArrived(request.getId());
            case COMPLETED:
                return groupPurchaseInfoRepository.updateStatusToCompleted(request.getId());
            case CANCELED:
                return groupPurchaseInfoRepository.updateStatusToCanceled(request.getId());
            default:
                return false;
        }
    }

    @Override
    public PageResult<MyInitiatedGroupPageResponse> myInitiatedGroup(MyInitiatedGroupPageRequest request) {
        return PageUtil.startPage(request, () -> {
            groupPurchaseInfoRepository.listGroupPurchaseInfo(GroupPurchaseInfoConvert.INSTANCE.convert(request));
        }, GroupPurchaseInfo.class, MyInitiatedGroupPageResponse.class);
//        return PageUtil.startPage(request, () -> {
//            groupPurchaseInfoRepository.listGroupPurchaseInfo(request);
//        }, list -> {
//            // fixme 这里类型丢失了，变成了Object
//            return GroupPurchaseInfoConvert.INSTANCE.convert( list);                                                                                                                                                                                                                                                                      ANCE.convert(list);
//        });
    }

    @Override
    public PageResult<MyInitiatedGroupPageResponse> pageList(MyInitiatedGroupPageRequest request) {
        return null;
    }

    @Override
    public PageResult<MyJoinGroupPageResponse> myJoinGroup(MyJoinGroupPageRequest request) {
        final PageResult<MyJoinGroupPageResponse> pageResult = PageUtil.startPage(request, () -> {
            groupPurchaseInfoRepository.myJoinGroup(GroupPurchaseInfoConvert.INSTANCE.convert(request));
        });
        if (pageResult.isEmpty()) {
            return pageResult;
        }
        final Set<Long> uidSet = pageResult.getContent()
                .stream()
                .map(MyJoinGroupPageResponse::getGroupMasterUid)
                .collect(Collectors.toSet());
        final Map<Long, UserInfo> userInfoMap = userInfoRepository.mapListUsers(uidSet);
        pageResult.getContent().forEach(group -> {
            UserInfo tempUser = userInfoMap.get(group.getGroupMasterUid());
            if (Objects.nonNull(tempUser)) {
                group.setGroupMasterName(tempUser.getNickname());
                group.setGroupMasterCommunityId(tempUser.getCommunityId());
                group.setGroupMasterBuildingNo(tempUser.getBuildingNo());
                group.setGroupMasterRoomNo(tempUser.getRoomNo());
            }
        });
        return pageResult;
    }
}
