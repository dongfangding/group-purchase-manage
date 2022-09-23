package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.boot.common.core.util.DateUtils;
import com.ddf.group.purchase.api.enume.GroupPurchaseStatusEnum;
import com.ddf.group.purchase.core.mapper.GroupPurchaseInfoMapper;
import com.ddf.group.purchase.core.mapper.GroupPurchaseTraceMapper;
import com.ddf.group.purchase.core.model.cqrs.group.MyInitiatedGroupQuery;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseTrace;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/06/29 09:55
 */
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GroupPurchaseInfoRepository {

    private final GroupPurchaseInfoMapper groupPurchaseInfoMapper;
    private final GroupPurchaseTraceMapper groupPurchaseTraceMapper;


    /**
     * 添加团购记录
     *
     * @param groupPurchaseInfo
     * @return
     */
    public boolean insertGroupPurchaseInfo(GroupPurchaseInfo groupPurchaseInfo) {
        final boolean result = groupPurchaseInfoMapper.insert(groupPurchaseInfo) > 0;
        if (result) {
            final GroupPurchaseTrace trace = new GroupPurchaseTrace();
            trace.setGroupPurchaseId(groupPurchaseInfo.getId());
            trace.setStatus(GroupPurchaseStatusEnum.CREATED.getValue());
            trace.setCtime(DateUtils.currentTimeSeconds());
            insertTrace(trace);
        }
        return result;
    }

    /**
     * 根据id查询团购信息
     *
     * @param id
     * @return
     */
    public GroupPurchaseInfo selectGroupPurchaseInfoById(Long id) {
        return groupPurchaseInfoMapper.selectById(id);
    }

    /**
     * 团购列表查询
     *
     * @param query
     * @return
     */
    public List<GroupPurchaseInfo> listGroupPurchaseInfo(MyInitiatedGroupQuery query) {
        final LambdaQueryWrapper<GroupPurchaseInfo> wrapper = Wrappers.lambdaQuery();
        if (Objects.nonNull(query.getGroupMasterUid())) {
            wrapper.eq(GroupPurchaseInfo::getGroupMasterUid, query.getGroupMasterUid());
        }
        if (Objects.nonNull(query.getStatus())) {
            wrapper.eq(GroupPurchaseInfo::getStatus, query.getStatus().getValue());
        }
        if (StringUtils.isNotBlank(query.getGroupName())) {
            wrapper.likeLeft(GroupPurchaseInfo::getName, query.getGroupName());
        }
        return groupPurchaseInfoMapper.selectList(wrapper);
    }

    /**
     * 修改团购基本信息
     *
     * @param id
     * @param groupName
     * @param content
     * @return
     */
    public boolean modifyGroupBaseInfo(Long id, String groupName, String content) {
        final LambdaUpdateWrapper<GroupPurchaseInfo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(GroupPurchaseInfo::getId, id);
        wrapper.set(GroupPurchaseInfo::getName, groupName);
        wrapper.set(GroupPurchaseInfo::getContent, content);
        wrapper.set(GroupPurchaseInfo::getMtime, DateUtils.currentTimeSeconds());
        return groupPurchaseInfoMapper.update(null, wrapper) > 0;
    }

    /**
     * 修改团购状态为已成团
     *
     * @param id
     * @return
     */
    public boolean updateStatusToGrouped(Long id) {
        final LambdaUpdateWrapper<GroupPurchaseInfo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(GroupPurchaseInfo::getId, id);
        wrapper.eq(GroupPurchaseInfo::getStatus, GroupPurchaseStatusEnum.CREATED.getValue());
        wrapper.set(GroupPurchaseInfo::getStatus, GroupPurchaseStatusEnum.GROUPED.getValue());
        wrapper.set(GroupPurchaseInfo::getMtime, DateUtils.currentTimeSeconds());
        final boolean result = groupPurchaseInfoMapper.update(null, wrapper) > 0;
        if (result) {
            final GroupPurchaseTrace trace = new GroupPurchaseTrace();
            trace.setGroupPurchaseId(id);
            trace.setStatus(GroupPurchaseStatusEnum.GROUPED.getValue());
            trace.setCtime(DateUtils.currentTimeSeconds());
            insertTrace(trace);
        }
        return result;
    }

    /**
     * 修改团购状态为已到货
     *
     * @param id
     * @return
     */
    public boolean updateStatusToArrived(Long id) {
        final LambdaUpdateWrapper<GroupPurchaseInfo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(GroupPurchaseInfo::getId, id);
        wrapper.eq(GroupPurchaseInfo::getStatus, GroupPurchaseStatusEnum.GROUPED.getValue());
        wrapper.set(GroupPurchaseInfo::getStatus, GroupPurchaseStatusEnum.ARRIVED.getValue());
        wrapper.set(GroupPurchaseInfo::getMtime, DateUtils.currentTimeSeconds());
        final boolean result = groupPurchaseInfoMapper.update(null, wrapper) > 0;
        if (result) {
            final GroupPurchaseTrace trace = new GroupPurchaseTrace();
            trace.setGroupPurchaseId(id);
            trace.setStatus(GroupPurchaseStatusEnum.ARRIVED.getValue());
            trace.setCtime(DateUtils.currentTimeSeconds());
            insertTrace(trace);
        }
        return result;
    }

    /**
     * 修改团购状态为已完成
     *
     * @param id
     * @return
     */
    public boolean updateStatusToCompleted(Long id) {
        final LambdaUpdateWrapper<GroupPurchaseInfo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(GroupPurchaseInfo::getId, id);
        wrapper.eq(GroupPurchaseInfo::getStatus, GroupPurchaseStatusEnum.ARRIVED.getValue());
        wrapper.set(GroupPurchaseInfo::getStatus, GroupPurchaseStatusEnum.COMPLETED.getValue());
        wrapper.set(GroupPurchaseInfo::getMtime, DateUtils.currentTimeSeconds());
        final boolean result = groupPurchaseInfoMapper.update(null, wrapper) > 0;
        if (result) {
            final GroupPurchaseTrace trace = new GroupPurchaseTrace();
            trace.setGroupPurchaseId(id);
            trace.setStatus(GroupPurchaseStatusEnum.COMPLETED.getValue());
            trace.setCtime(DateUtils.currentTimeSeconds());
            insertTrace(trace);
        }
        return result;
    }

    /**
     * 修改团购状态为已取消
     *
     * @param id
     * @return
     */
    public boolean updateStatusToCanceled(Long id) {
        final LambdaUpdateWrapper<GroupPurchaseInfo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(GroupPurchaseInfo::getId, id);
        wrapper.set(GroupPurchaseInfo::getStatus, GroupPurchaseStatusEnum.CANCELED.getValue());
        wrapper.set(GroupPurchaseInfo::getMtime, DateUtils.currentTimeSeconds());
        final boolean result = groupPurchaseInfoMapper.update(null, wrapper) > 0;
        if (result) {
            final GroupPurchaseTrace trace = new GroupPurchaseTrace();
            trace.setGroupPurchaseId(id);
            trace.setStatus(GroupPurchaseStatusEnum.CANCELED.getValue());
            trace.setCtime(DateUtils.currentTimeSeconds());
            insertTrace(trace);
        }
        return result;
    }

    /**
     * 添加团购跟踪状态
     *
     * @param groupPurchaseTrace
     * @return
     */
    public boolean insertTrace(GroupPurchaseTrace groupPurchaseTrace) {
        return groupPurchaseTraceMapper.insert(groupPurchaseTrace) > 0;
    }

    /**
     * 查询团购状态跟踪
     *
     * @param groupId
     * @return
     */
    public List<GroupPurchaseTrace> listGroupTrace(Long groupId) {
        final LambdaQueryWrapper<GroupPurchaseTrace> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GroupPurchaseTrace::getGroupPurchaseId, groupId)
                .orderByAsc(GroupPurchaseTrace::getCtime);
        return groupPurchaseTraceMapper.selectList(wrapper);
    }



    /**
     * 更新团购发布状态
     *
     * @param id
     * @param groupMasterUid
     * @param publicFlag
     * @return
     */
    public boolean updatePublicFlag(Long id, Long groupMasterUid, boolean publicFlag) {
        final LambdaUpdateWrapper<GroupPurchaseInfo> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(GroupPurchaseInfo::getId, id)
                .eq(GroupPurchaseInfo::getGroupMasterUid, groupMasterUid);
        wrapper.set(GroupPurchaseInfo::getPublicFlag, publicFlag);
        return groupPurchaseInfoMapper.update(null, wrapper) > 0;
    }

}
