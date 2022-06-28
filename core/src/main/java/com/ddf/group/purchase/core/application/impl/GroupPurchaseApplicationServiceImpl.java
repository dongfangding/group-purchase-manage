package com.ddf.group.purchase.core.application.impl;

import cn.hutool.core.collection.CollUtil;
import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.api.request.group.GroupPurchaseInfoPageRequest;
import com.ddf.group.purchase.api.response.group.GroupPurchaseInfoPageResponse;
import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import com.ddf.group.purchase.core.client.UserClient;
import com.ddf.group.purchase.core.exception.ExceptionCode;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseInfoExtMapper;
import com.ddf.group.purchase.core.mapper.ext.UserJoinGroupInfoExtMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import com.ddf.group.purchase.core.model.entity.UserJoinGroupInfo;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private final GroupPurchaseInfoExtMapper groupPurchaseInfoExtMapper;
    private final UserInfoRepository userInfoRepository;
    private final UserJoinGroupInfoExtMapper userJoinGroupInfoExtMapper;

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
        groupPurchaseInfoExtMapper.insert(groupPurchaseInfo);

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
            userJoinGroupInfoExtMapper.batchInsert(joins);
        }
    }

    @Override
    public PageInfo<GroupPurchaseInfoPageResponse> pageList(GroupPurchaseInfoPageRequest request) {
        return null;
    }
}
