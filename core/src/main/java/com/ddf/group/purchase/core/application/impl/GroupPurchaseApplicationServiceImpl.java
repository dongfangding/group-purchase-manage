package com.ddf.group.purchase.core.application.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.ddf.boot.common.authentication.util.UserContextUtil;
import com.ddf.boot.common.core.exception200.BusinessException;
import com.ddf.boot.common.core.model.PageResult;
import com.ddf.boot.common.core.util.BeanUtil;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.boot.common.core.util.PageUtil;
import com.ddf.boot.common.core.util.PatternUtil;
import com.ddf.boot.common.core.util.PreconditionUtil;
import com.ddf.boot.common.core.util.RandomExtUtil;
import com.ddf.boot.common.rocketmq.dto.MessageRequest;
import com.ddf.boot.common.rocketmq.dto.RocketMQDelayTimeMapping;
import com.ddf.boot.common.rocketmq.helper.RocketMQHelper;
import com.ddf.group.purchase.api.enume.GroupPurchaseItemJoinStatusEnum;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.api.request.group.CustomizeCreateRequest;
import com.ddf.group.purchase.api.request.group.JoinGroupRequest;
import com.ddf.group.purchase.api.request.group.ModifyGroupRequest;
import com.ddf.group.purchase.api.request.group.MyInitiatedGroupPageRequest;
import com.ddf.group.purchase.api.request.group.MyJoinGroupPageRequest;
import com.ddf.group.purchase.api.request.group.PublishGroupRequest;
import com.ddf.group.purchase.api.request.group.SimulationPayRequest;
import com.ddf.group.purchase.api.request.group.SubscribeGroupRequest;
import com.ddf.group.purchase.api.request.group.UpdateGroupStatusRequest;
import com.ddf.group.purchase.api.response.group.GroupItemResponse;
import com.ddf.group.purchase.api.response.group.GroupPurchaseListResponse;
import com.ddf.group.purchase.api.response.group.GroupStatisticsDTO;
import com.ddf.group.purchase.api.response.group.MarketplaceGroupPurchaseListResponse;
import com.ddf.group.purchase.api.response.group.MyInitiatedGroupPageResponse;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.api.response.group.OrderDetailResponse;
import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import com.ddf.group.purchase.core.assembler.GroupAssembler;
import com.ddf.group.purchase.core.client.MailClient;
import com.ddf.group.purchase.core.client.UserClient;
import com.ddf.group.purchase.core.constants.RocketMQConst;
import com.ddf.group.purchase.core.converter.GroupPurchaseInfoConvert;
import com.ddf.group.purchase.core.converter.GroupPurchaseItemGoodConverter;
import com.ddf.group.purchase.core.converter.UserAddressConvert;
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
import com.ddf.group.purchase.core.pubsub.event.GroupPayEvent;
import com.ddf.group.purchase.core.pubsub.event.GroupViewEvent;
import com.ddf.group.purchase.core.repository.GroupPurchaseGoodRepository;
import com.ddf.group.purchase.core.repository.GroupPurchaseInfoRepository;
import com.ddf.group.purchase.core.repository.GroupPurchaseItemGoodRepository;
import com.ddf.group.purchase.core.repository.GroupPurchaseItemRepository;
import com.ddf.group.purchase.core.repository.GroupStatisticsRepository;
import com.ddf.group.purchase.core.repository.UserInfoRepository;
import com.google.common.collect.Sets;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private final GroupPurchaseGoodExtMapper groupPurchaseGoodExtMapper;
    private final GroupPurchaseInfoExtMapper groupPurchaseInfoExtMapper;
    private final GroupPurchaseGoodRepository groupPurchaseGoodRepository;
    private final GroupPurchaseItemGoodRepository groupPurchaseItemGoodRepository;
    private final GroupPurchaseItemRepository groupPurchaseItemRepository;
    private final GroupPurchaseItemExtMapper groupPurchaseItemExtMapper;
    private final GroupPurchaseItemGoodExtMapper groupPurchaseItemGoodExtMapper;
    private final MailClient mailClient;
    private final RocketMQHelper rocketMQHelper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final GroupAssembler groupAssembler;
    private final GroupStatisticsRepository groupStatisticsRepository;

    @Override
    public GroupPurchaseListResponse groupDetail(Long groupId) {
        final GroupPurchaseListResponse details = groupPurchaseInfoExtMapper.details(groupId);
        details.setJoinItems(joinInfo(groupId));
        // 发布团购被预览事件
        applicationEventPublisher.publishEvent(new GroupViewEvent(
                this, GroupViewEvent.Body.builder()
                .groupId(groupId)
                .currentUserId(UserContextUtil.getLongUserId())
                .build()));
        return details;
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

        List<GroupPurchaseItem> joins = new ArrayList<>();
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
                final GroupPurchaseItem joinGroupInfo = new GroupPurchaseItem();
                joinGroupInfo.setGroupPurchaseId(groupPurchaseInfo.getId());
                joinGroupInfo.setJoinUid(user.getId());
                joinGroupInfo.setCtime(currentTimeSeconds);
                joinGroupInfo.setSubscribeProgress(Boolean.TRUE);
                joins.add(joinGroupInfo);
            }
        }
        if (CollUtil.isNotEmpty(joins)) {
            groupPurchaseItemRepository.batchInsertUserJoinGroup(joins);
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
        groupPurchaseInfo.setServiceCommunityId(Objects.nonNull(userInfo.getCommunityId()) ? userInfo.getCommunityId().longValue() : 0);
        if (StringUtils.isNotBlank(groupPurchaseInfo.getContent())) {
            // 解析出图片链接
            final Set<String> imgSrcUrl = PatternUtil.findImgSrcUrl(groupPurchaseInfo.getContent());
            if (CollUtil.isNotEmpty(imgSrcUrl)) {
                groupPurchaseInfo.setPicUrls(CollectionUtil.join(imgSrcUrl, ","));
            }
        }
        final boolean bool = groupPurchaseInfoRepository.insertGroupPurchaseInfo(groupPurchaseInfo);
        if (bool) {
            final GroupPurchaseGood good = GroupPurchaseInfoConvert.INSTANCE.convertGood(request);
            good.setGroupPurchaseId(groupPurchaseInfo.getId());
            good.setRemainingStock(good.getStock());
            groupPurchaseGoodExtMapper.insert(good);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyGroupInfo(ModifyGroupRequest request) {
        final GroupPurchaseInfo groupPurchaseInfo = groupPurchaseInfoRepository.selectGroupPurchaseInfoById(request.getId());
        PreconditionUtil.checkArgument(Objects.nonNull(groupPurchaseInfo), ExceptionCode.RECORD_NOT_EXIST);
        PreconditionUtil.checkArgument(Objects.equals(groupPurchaseInfo.getGroupMasterUid(), UserContextUtil.getLongUserId()),
                ExceptionCode.RECORD_NOT_EXIST);
//        PreconditionUtil.checkArgument(!groupPurchaseInfo.getPublicFlag(), ExceptionCode.RECORD_STATUS_NOT_ALLOW_MODIFY);
        PreconditionUtil.checkArgument(Objects.equals(GroupPurchaseStatusEnum.CREATED.getValue(), groupPurchaseInfo.getStatus()),
                ExceptionCode.RECORD_STATUS_NOT_ALLOW_MODIFY);
        BeanUtil.copy(request, groupPurchaseInfo);
        if (StringUtils.isNotBlank(groupPurchaseInfo.getContent())) {
            // 解析出图片链接
            final Set<String> imgSrcUrl = PatternUtil.findImgSrcUrl(groupPurchaseInfo.getContent());
            if (CollUtil.isNotEmpty(imgSrcUrl)) {
                groupPurchaseInfo.setPicUrls(CollectionUtil.join(imgSrcUrl, ","));
            }
        }
        // 修改团购基本信息
        final int i = groupPurchaseInfoExtMapper.updateById(groupPurchaseInfo);
        final GroupPurchaseGood purchaseGood = groupPurchaseGoodRepository.getByGroupId(groupPurchaseInfo.getId());
        if (Objects.nonNull(purchaseGood)) {
            BeanUtil.copy(request, purchaseGood);
            groupPurchaseGoodExtMapper.updateById(purchaseGood);
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
            final List<Long> joinUids = groupPurchaseItemRepository.selectJoinPaidUids(request.getId());
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
    }

    @Override
    public PageResult<MyInitiatedGroupPageResponse> pageList(MyInitiatedGroupPageRequest request) {
        return null;
    }

    @Override
    public PageResult<MyJoinGroupPageResponse> myJoinGroup(MyJoinGroupPageRequest request) {
        final PageResult<MyJoinGroupPageResponse> pageResult = PageUtil.startPage(request, () -> {
            groupPurchaseItemRepository.myJoinGroup(GroupPurchaseInfoConvert.INSTANCE.convert(request));
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
                group.setGroupMasterAvatarUrl(tempUser.getAvatarUrl());
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
        // 已有人支付，不可取消发布？或者不应该控制，万一订单本身有问题，比如价格有问题，要止损之类的不能阻止，只是不能影响已有订单。
//        PreconditionUtil.checkArgument(groupStatisticsRepository.getStatisticsValue(purchaseInfo.getId(), GroupStatisticsTypeEnum.PAY) <= 0, ExceptionCode.RECORD_STATUS_NOT_ALLOW_UPDATE);
        // fixme 通知，只要没成团，可以任意修改状态
        PreconditionUtil.checkArgument(groupPurchaseInfoRepository.updatePublicFlag(request.getId(), UserContextUtil.getLongUserId(), request.isPublicFlag()), ExceptionCode.RECORD_STATUS_NOT_ALLOW_UPDATE);
        return true;
    }

    @Override
    public boolean subscribeGroup(SubscribeGroupRequest request) {
        return groupPurchaseItemRepository.subscribe(request.getGroupId(), UserContextUtil.getLongUserId(), request.isSubscribeFlag());
    }

    @Override
    public PageResult<MarketplaceGroupPurchaseListResponse> marketplaceGroupPageList(
            MyInitiatedGroupPageRequest request) {
        final GroupListQuery query = GroupPurchaseInfoConvert.INSTANCE.convert(request);
        query.setGroupMasterUid(null);
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
        final List<Long> groupIdList = result.getContent()
                .stream()
                .map(GroupPurchaseListResponse::getId)
                .collect(Collectors.toList());
        final Map<Long, UserInfo> map = userInfoRepository.mapListUsers(uidList);
        // 获取这批团购数据的统计信息
        final Map<Long, GroupStatisticsDTO> statisticsDTOMap = groupAssembler.mapGroupStatistics(groupIdList);
        final List<MarketplaceGroupPurchaseListResponse> listResponses = result.getContent()
                .stream()
                .map(val -> {
                    final MarketplaceGroupPurchaseListResponse dto = BeanUtil.copy(
                            val, MarketplaceGroupPurchaseListResponse.class);
                    dto.setStatistics(statisticsDTOMap.get(val.getId()));
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
    @Transactional(rollbackFor = Exception.class)
    public void join(JoinGroupRequest request) {
        final Long groupId = request.getGroupId();
        final Long goodId = request.getGoodId();
        final Long currentUserId = UserContextUtil.getLongUserId();
        // 团购校验
        checkGroupUserCanOperate(groupId);
        final GroupPurchaseGood good = groupPurchaseGoodExtMapper.selectById(goodId);
        PreconditionUtil.checkArgument(Objects.nonNull(good), ExceptionCode.GROUP_GOOD_NOT_EXIST);

        // 剩余支付之间
        final RocketMQDelayTimeMapping delayTimeMapping = RocketMQDelayTimeMapping.M2;

        final Long currentTimeSeconds = DateUtils.currentTimeSeconds();
        GroupPurchaseItem purchaseItem = new GroupPurchaseItem();
        purchaseItem.setOrderNo(RandomExtUtil.randomOrderNo(32));
        purchaseItem.setGroupPurchaseId(groupId);
        purchaseItem.setJoinUid(currentUserId);
        purchaseItem.setJoinStatus(GroupPurchaseItemJoinStatusEnum.WAIT_PAY.getValue());
        purchaseItem.setCtime(currentTimeSeconds);
        purchaseItem.setSubscribeProgress(Boolean.TRUE);
        purchaseItem.setStatusChangeTime(currentTimeSeconds);
        purchaseItem.setPayCountDownSeconds(delayTimeMapping.getSeconds().intValue());
        groupPurchaseItemExtMapper.insert(purchaseItem);

        GroupPurchaseItemGood purchaseItemGood = new GroupPurchaseItemGood();
        purchaseItemGood.setCtime(currentTimeSeconds);
        purchaseItemGood.setGroupPurchaseId(groupId);
        purchaseItemGood.setGroupPurchaseItemId(purchaseItem.getId());
        purchaseItemGood.setGroupPurchaseGoodId(goodId);
        purchaseItemGood.setGroupPurchaseGoodName(good.getGoodName());
        purchaseItemGood.setGroupPurchaseGoodPic(good.getGoodPic());
        purchaseItemGood.setPrice(good.getPrice());
        purchaseItemGood.setGoodNum(request.getGoodNum());
        purchaseItemGood.setTotalPrice(good.getPrice().multiply(BigDecimal.valueOf(request.getGoodNum())));
        purchaseItemGood.setGoodDescription(good.getGoodDescription());
        purchaseItemGood.setJoinUid(currentUserId);
        purchaseItemGood.setCtime(currentTimeSeconds);
        purchaseItemGood.setMtime(currentTimeSeconds);
        groupPurchaseItemGoodExtMapper.insert(purchaseItemGood);

        final MessageRequest<Object> messageRequest = MessageRequest.builder()
                .topic(RocketMQConst.Topic.DELAY_TOPIC)
                .tag(RocketMQConst.GroupOrder.TAG)
                .level(delayTimeMapping.getLevel())
                .bizId(String.join("-", RocketMQConst.GroupOrder.TAG, purchaseItem.getId() + ""))
                .body(purchaseItem.getId())
                .build();
        rocketMQHelper.syncSend(messageRequest);
    }

    @Override
    public List<GroupItemResponse> joinInfo(Long groupId) {
        final List<GroupItemResponse> responses = groupPurchaseItemExtMapper.listGroupItem(groupId);
        if (CollectionUtils.isEmpty(responses)) {
            return Collections.emptyList();
        }
        final Set<Long> uidSet = responses.stream()
                .map(GroupItemResponse::getJoinUid)
                .collect(Collectors.toSet());
        final Map<Long, UserInfo> map = userInfoRepository.mapListUsers(uidSet);
        responses.forEach(obj -> {
            if (map.containsKey(obj.getJoinUid())) {
                obj.setJoinUserName(map.get(obj.getJoinUid()).getNickname());
                obj.setJoinUserAvatarUrl(map.get(obj.getJoinUid()).getAvatarUrl());
            }
        });
        return responses;
    }

    @Override
    public boolean simulationPay(SimulationPayRequest request) {
        Long userId = UserContextUtil.getLongUserId();
        final Long joinItemId = request.getJoinItemId();
        final Long goodId = request.getGoodId();
        final Integer goodNum = request.getGoodNum();
        final GroupPurchaseItem item = groupPurchaseItemExtMapper.selectById(joinItemId);
        PreconditionUtil.checkArgument(Objects.equals(userId, item.getJoinUid()), ExceptionCode.DATA_NOT_MATCH_USER);
        if (!Objects.equals(GroupPurchaseItemJoinStatusEnum.WAIT_PAY.getValue(), item.getJoinStatus())) {
            throw new BusinessException(ExceptionCode.GROUP_ITEM_NOT_ALLOW_PAY);
        }
        final GroupPurchaseItemGood itemGood = groupPurchaseItemGoodRepository.selectUserGroupGood(item.getId(), userId, goodId);
        if (Objects.isNull(itemGood)) {
            throw new BusinessException(ExceptionCode.ORDER_GOOD_NOT_EXIST);
        }
        // 先执行库存扣减操作
        final int reduceGoodStockResult = groupPurchaseGoodExtMapper.reduceGoodStock(goodId, goodNum);
        if (reduceGoodStockResult < 1) {
            throw new BusinessException(ExceptionCode.GROUP_ORDER_STOCK_NOT_ENOUGH);
        }
        // 允许重新修改商品数量信息
        if (!Objects.equals(itemGood.getGoodNum(), goodNum)) {
            itemGood.setGoodNum(goodNum);
            itemGood.setTotalPrice(itemGood.getPrice().multiply(BigDecimal.valueOf(goodNum)));
            groupPurchaseItemGoodExtMapper.updateById(itemGood);
        }
        // 发布参团事件
        applicationEventPublisher.publishEvent(new GroupPayEvent(
                this, GroupPayEvent.Body.builder()
                .groupId(item.getGroupPurchaseId())
                .currentUserId(UserContextUtil.getLongUserId())
                .build()));
        return groupPurchaseItemExtMapper.updatePaid(joinItemId, UserAddressConvert.INSTANCE.convert(request), request.getRemark()) > 0;
    }

    @Override
    public boolean closeOrder(Long groupItemId) {
        final GroupPurchaseItem item = groupPurchaseItemExtMapper.selectById(groupItemId);
        if (Objects.isNull(item) || !Objects.equals(GroupPurchaseItemJoinStatusEnum.WAIT_PAY.getValue(), item.getJoinStatus())) {
            return false;
        }
        return groupPurchaseItemRepository.closeOrder(groupItemId);
    }

    @Override
    public OrderDetailResponse orderDetail(Long id) {
        final OrderDetailResponse response = groupPurchaseItemExtMapper.selectOrderDetail(id);
        if (Objects.isNull(response)) {
            return null;
        }
        final Map<Long, UserInfo> userMap = userInfoRepository.mapListUsers(
                Sets.newHashSet(response.getJoinUid(), response.getGroupMasterUid()));
        if (CollUtil.isNotEmpty(userMap)) {
            if (userMap.containsKey(response.getGroupMasterUid())) {
                response.setGroupMasterNickname(userMap.get(response.getGroupMasterUid()).getNickname());
                response.setGroupMasterAvatarUrl(userMap.get(response.getGroupMasterUid()).getAvatarUrl());
            }
            if (userMap.containsKey(response.getJoinUid())) {
                response.setJoinNickname(userMap.get(response.getGroupMasterUid()).getNickname());
                response.setJoinAvatarUrl(userMap.get(response.getGroupMasterUid()).getAvatarUrl());
            }
        }
        final Long userId = UserContextUtil.getLongUserId();
        final List<GroupPurchaseItemGood> itemGoods = groupPurchaseItemGoodRepository.listUserGroupGood(response.getGroupPurchaseId(), userId);
        if (CollUtil.isNotEmpty(itemGoods)) {
            response.setOrderGood(GroupPurchaseItemGoodConverter.INSTANCE.convert(itemGoods.get(0)));
        }
        return response;
    }

}
