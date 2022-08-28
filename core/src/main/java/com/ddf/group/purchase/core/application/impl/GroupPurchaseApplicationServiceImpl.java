package com.ddf.group.purchase.core.application.impl;

import cn.hutool.core.collection.CollUtil;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.model.PageResult;
import com.ddf.boot.common.core.util.BeanUtil;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.boot.common.core.util.PageUtil;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.api.request.group.CustomizeCreateRequest;
import com.ddf.group.purchase.api.request.group.JoinGroupRequest;
import com.ddf.group.purchase.api.request.group.ModifyGroupRequest;
import com.ddf.group.purchase.api.request.group.MyInitiatedGroupPageRequest;
import com.ddf.group.purchase.api.request.group.MyJoinGroupPageRequest;
import com.ddf.group.purchase.api.request.group.PublishGroupRequest;
import com.ddf.group.purchase.api.request.group.SubscribeGroupRequest;
import com.ddf.group.purchase.api.request.group.UpdateGroupStatusRequest;
import com.ddf.group.purchase.api.response.group.GroupPurchaseListResponse;
import com.ddf.group.purchase.api.response.group.MarketplaceGroupPurchaseListResponse;
import com.ddf.group.purchase.api.response.group.MyInitiatedGroupPageResponse;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import com.ddf.group.purchase.core.client.MailClient;
import com.ddf.group.purchase.core.client.UserClient;
import com.ddf.group.purchase.core.converter.GroupPurchaseInfoConvert;
import com.ddf.group.purchase.core.exception.ExceptionCode;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseGoodExtMapper;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseInfoExtMapper;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseItemExtMapper;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseItemGoodExtMapper;
import com.ddf.group.purchase.core.model.cqrs.group.GroupListQuery;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseGood;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItem;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItemGood;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import com.ddf.group.purchase.core.model.entity.UserJoinGroupInfo;
import com.ddf.group.purchase.core.repository.GroupPurchaseGoodRepository;
import com.ddf.group.purchase.core.repository.GroupPurchaseInfoRepository;
import com.ddf.group.purchase.core.repository.GroupPurchaseItemGoodRepository;
import com.ddf.group.purchase.core.repository.GroupPurchaseItemRepository;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import com.ddf.group.purchase.core.repository.UserJoinGroupInfoRepository;
import java.util.ArrayList;
import java.util.HashSet;
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
    private final UserJoinGroupInfoRepository userJoinGroupInfoRepository;
    private final GroupPurchaseGoodExtMapper groupPurchaseGoodExtMapper;
    private final GroupPurchaseInfoExtMapper groupPurchaseInfoExtMapper;
    private final GroupPurchaseGoodRepository groupPurchaseGoodRepository;
    private final GroupPurchaseItemGoodRepository groupPurchaseItemGoodRepository;
    private final GroupPurchaseItemRepository groupPurchaseItemRepository;
    private final GroupPurchaseItemExtMapper groupPurchaseItemExtMapper;
    private final GroupPurchaseItemGoodExtMapper groupPurchaseItemGoodExtMapper;
    private final MailClient mailClient;

    @Override
    public GroupPurchaseListResponse groupDetail(Long groupId) {
        return groupPurchaseInfoExtMapper.details(groupId);
    }

    @Override
    public GroupPurchaseInfo checkGroupUserCanOperate(Long groupId) {
        final GroupPurchaseInfo info = groupPurchaseInfoRepository.selectGroupPurchaseInfoById(groupId);
        PreconditionUtil.checkArgument(Objects.nonNull(info), ExceptionCode.RECORD_NOT_EXIST);
        PreconditionUtil.checkArgument(info.getPublicFlag(), ExceptionCode.GROUP_NOT_PUBLISH);
        PreconditionUtil.checkArgument(Objects.equals(GroupPurchaseStatusEnum.CREATED.getValue(), info.getStatus()),
                ExceptionCode.RECORD_GROUPED_NOT_ALLOW_JOIN);
        final Long timeSeconds = DateUtils.currentTimeSeconds();
        if (timeSeconds < info.getStartTime()) {
            throw new BusinessException(ExceptionCode.GROUP_TIME_NOT_START);
        }
        if (timeSeconds > info.getEndTime()) {
            throw new BusinessException(ExceptionCode.GROUP_TIME_OVER_END);
        }
        // TODO 服务小区校验
        return info;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFromWxJieLong(CreateFromWxJieLongRequest request) {
        final UserInfo userInfo = userClient.currentUserInfo();
        // 解析出来的数据
        final CreateFromWxJieLongRequest.Data data = request.parseData();
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
        groupPurchaseInfo.setContent(data.getExample() + "\n" + data.getRemarks());
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
            userJoinGroupInfoRepository.batchInsertUserJoinGroup(joins);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void customizeCreate(CustomizeCreateRequest request) {
        final Long currentTimeSeconds = DateUtils.currentTimeSeconds();
        final GroupPurchaseInfo groupPurchaseInfo = GroupPurchaseInfoConvert.INSTANCE.convertGroup(request);
        groupPurchaseInfo.setGroupMasterUid(UserContextUtil.getLongUserId());
        final UserInfo userInfo = userInfoRepository.getById(UserContextUtil.getLongUserId());
        groupPurchaseInfo.setStatus(GroupPurchaseStatusEnum.CREATED.getValue());
        groupPurchaseInfo.setCtime(currentTimeSeconds);
        groupPurchaseInfo.setMtime(currentTimeSeconds);
        groupPurchaseInfo.setServiceCommunityId(userInfo.getCommunityId().longValue());
        final boolean bool = groupPurchaseInfoRepository.insertGroupPurchaseInfo(groupPurchaseInfo);
        if (bool) {
            final GroupPurchaseGood good = GroupPurchaseInfoConvert.INSTANCE.convertGood(request);
            good.setGroupPurchaseId(groupPurchaseInfo.getId());
            groupPurchaseGoodExtMapper.insert(good);
        }
    }

    @Override
    public void modifyGroupInfo(ModifyGroupRequest request) {
        final GroupPurchaseInfo groupPurchaseInfo = groupPurchaseInfoRepository.selectGroupPurchaseInfoById(request.getId());
        PreconditionUtil.checkArgument(Objects.nonNull(groupPurchaseInfo), ExceptionCode.RECORD_NOT_EXIST);
        PreconditionUtil.checkArgument(Objects.equals(groupPurchaseInfo.getGroupMasterUid(), UserContextUtil.getLongUserId()),
                ExceptionCode.RECORD_NOT_EXIST);
        PreconditionUtil.checkArgument(Objects.equals(GroupPurchaseStatusEnum.CREATED.getValue(), groupPurchaseInfo.getStatus()),
                ExceptionCode.RECORD_STATUS_NOT_ALLOW_MODIFY);
        BeanUtil.copy(request, groupPurchaseInfo);
        // 修改团购基本信息
        final int i = groupPurchaseInfoExtMapper.updateById(groupPurchaseInfo);
        if (i > 0) {
            final GroupPurchaseGood purchaseGood = groupPurchaseGoodRepository.getByGroupId(groupPurchaseInfo.getId());
            if (Objects.nonNull(purchaseGood)) {
                BeanUtil.copy(purchaseGood, request);
                groupPurchaseGoodExtMapper.updateById(purchaseGood);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGroupStatus(UpdateGroupStatusRequest request) {
        boolean result;
        switch (request.getStatus()) {
            case ARRIVED:
                result = groupPurchaseInfoRepository.updateStatusToArrived(request.getId());
                break;
            case COMPLETED:
                result = groupPurchaseInfoRepository.updateStatusToCompleted(request.getId());
                break;
            case CANCELED:
                result = groupPurchaseInfoRepository.updateStatusToCanceled(request.getId());
                break;
            default:
                result = false;
                break;
        }
        if (result) {
            final List<Long> joinUids = userJoinGroupInfoRepository.selectJoinUids(request.getId());
            if (CollUtil.isNotEmpty(joinUids)) {
                final GroupPurchaseInfo groupPurchaseInfo = groupPurchaseInfoRepository.selectGroupPurchaseInfoById(request.getId());
                final Map<Long, UserInfo> userInfoMap = userInfoRepository.mapListUsers(new HashSet<>(joinUids));
                userInfoMap.forEach((uid, user) -> {
                    if (StringUtils.isNotBlank(user.getEmail()) && user.getEmailVerified()) {
                        mailClient.sendGroupLatestInfo(user.getEmail(), groupPurchaseInfo.getName(), request.getStatus());
                    }
                });
            }
        }
        return result;
    }

    @Override
    public PageResult<GroupPurchaseListResponse> myInitiatedGroup(MyInitiatedGroupPageRequest request) {
        return PageUtil.startPage(request, () -> {
            groupPurchaseInfoExtMapper.list(GroupPurchaseInfoConvert.INSTANCE.convert(request));
        }, GroupPurchaseInfo.class, GroupPurchaseListResponse.class);
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
            userJoinGroupInfoRepository.myJoinGroup(GroupPurchaseInfoConvert.INSTANCE.convert(request));
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

    @Override
    public boolean publishGroup(PublishGroupRequest request) {
        final GroupPurchaseInfo purchaseInfo = groupPurchaseInfoRepository.selectGroupPurchaseInfoById(request.getId());
        // fixme 通知，只要没成团，可以任意修改状态
        PreconditionUtil.checkArgument(groupPurchaseInfoRepository.updatePublicFlag(request.getId(), UserContextUtil.getLongUserId(), request.isPublicFlag()), ExceptionCode.RECORD_STATUS_NOT_ALLOW_UPDATE);
        return true;
    }

    @Override
    public boolean subscribeGroup(SubscribeGroupRequest request) {
        return userJoinGroupInfoRepository.subscribe(request.getGroupId(), UserContextUtil.getLongUserId(), request.isSubscribeFlag());
    }

    @Override
    public PageResult<MarketplaceGroupPurchaseListResponse> marketplaceGroupPageList(
            MyInitiatedGroupPageRequest request) {
        final GroupListQuery query = GroupPurchaseInfoConvert.INSTANCE.convert(request);
        query.setPublicFlag(Boolean.TRUE);
        final PageResult<GroupPurchaseListResponse> result = PageUtil.startPage(request, () -> {
            groupPurchaseInfoExtMapper.list(query);
        });
        if (result.isEmpty()) {
            return PageUtil.empty(request);
        }
        final Set<Long> uidList = result.getContent()
                .stream()
                .map(GroupPurchaseListResponse::getGroupMasterUid)
                .collect(Collectors.toSet());
        final Map<Long, UserInfo> map = userInfoRepository.mapListUsers(uidList);
        final List<MarketplaceGroupPurchaseListResponse> listResponses = result.getContent()
                .stream()
                .map(val -> {
                    final MarketplaceGroupPurchaseListResponse dto = BeanUtil.copy(
                            val, MarketplaceGroupPurchaseListResponse.class);
                    if (map.containsKey(val.getGroupMasterUid())) {
                        dto.setGroupMasterName(map.get(val.getGroupMasterUid())
                                .getNickname());
                        dto.setGroupMasterAvatarUrl(map.get(val.getGroupMasterUid())
                                .getAvatarUrl());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        return PageUtil.ofPageRequest(request, result.getTotal(), listResponses);
    }

    @Override
    public void join(JoinGroupRequest request) {
        final Long groupId = request.getGroupId();
        final Long goodId = request.getGoodId();
        final GroupPurchaseInfo purchaseInfo = checkGroupUserCanOperate(groupId);
        final GroupPurchaseGood good = groupPurchaseGoodExtMapper.selectById(goodId);
        PreconditionUtil.checkArgument(Objects.nonNull(good), ExceptionCode.GROUP_GOOD_NOT_EXIST);

        final Long currentUserId = UserContextUtil.getLongUserId();
        final Long currentTimeSeconds = DateUtils.currentTimeSeconds();
        GroupPurchaseItem purchaseItem = groupPurchaseItemRepository.selectUserGroupItem(groupId, goodId);
        if (Objects.isNull(purchaseItem)) {
            purchaseItem = new GroupPurchaseItem();
            purchaseItem.setGroupPurchaseId(groupId);
            purchaseItem.setJoinUid(currentUserId);
            purchaseItem.setCtime(currentTimeSeconds);
            purchaseItem.setSubscribeProgress(Boolean.TRUE);
            groupPurchaseItemExtMapper.insert(purchaseItem);
        }

        GroupPurchaseItemGood purchaseItemGood = groupPurchaseItemGoodRepository.selectUserGroupGood(
                groupId, currentUserId, goodId);
        if (Objects.isNull(purchaseItemGood)) {
            purchaseItemGood = new GroupPurchaseItemGood();
            purchaseItemGood.setCtime(currentTimeSeconds);
            purchaseItemGood.setGroupPurchaseId(groupId);
            purchaseItemGood.setGroupPurchaseItemId(purchaseItem.getId());
            purchaseItemGood.setGroupPurchaseGoodId(goodId);
            purchaseItemGood.setGroupPurchaseGoodName(good.getGoodName());
            purchaseItemGood.setJoinUid(currentUserId);
            purchaseItemGood.setGoodNum(request.getGoodNum());
            purchaseItemGood.setCtime(currentTimeSeconds);
            purchaseItemGood.setMtime(currentTimeSeconds);
            groupPurchaseItemGoodExtMapper.insert(purchaseItemGood);
        } else {
            purchaseItemGood.setGoodNum(request.getGoodNum());
            purchaseItemGood.setMtime(currentTimeSeconds);
            groupPurchaseItemGoodExtMapper.updateById(purchaseItemGood);
        }
    }


}
