package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseItemExtMapper;
import com.ddf.group.purchase.core.model.cqrs.group.MyJoinGroupQuery;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItem;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>团购明细商品</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/28 15:52
 */
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GroupPurchaseItemRepository {

    private final GroupPurchaseItemExtMapper groupPurchaseItemExtMapper;


    /**
     * 查找用户指定团购的参团主表信息
     *
     * @param groupId
     * @param userId
     * @return
     */
    public GroupPurchaseItem selectUserGroupItem(Long groupId, Long userId) {
        final LambdaQueryWrapper<GroupPurchaseItem> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GroupPurchaseItem::getGroupPurchaseId, groupId)
                .eq(GroupPurchaseItem::getJoinUid, userId);
        return groupPurchaseItemExtMapper.selectOne(wrapper);
    }

    /**
     * 批量插入用户参团记录
     *
     * @param joins
     */
    public void batchInsertUserJoinGroup(List<GroupPurchaseItem> joins) {
        groupPurchaseItemExtMapper.batchInsert(joins);
    }

    /**
     * 我的参团列表查询
     *
     * @param query
     * @return
     */
    public List<MyJoinGroupPageResponse> myJoinGroup(MyJoinGroupQuery query) {
        return groupPurchaseItemExtMapper.myJoinGroup(query);
    }


    /**
     * 查询团购下的参团用户id集合
     *
     * @param groupId
     * @return
     */
    public List<Long> selectJoinUids(Long groupId) {
        return groupPurchaseItemExtMapper.selectJoinUids(groupId);
    }

    /**
     * 设置用户参团订阅状态
     *
     * @param groupId
     * @param joinUid
     * @param subscribeFlag
     * @return
     */
    public boolean subscribe(Long groupId, Long joinUid, boolean subscribeFlag) {
        final LambdaUpdateWrapper<GroupPurchaseItem> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(GroupPurchaseItem::getGroupPurchaseId, groupId)
                .eq(GroupPurchaseItem::getJoinUid, joinUid);
        wrapper.set(GroupPurchaseItem::getSubscribeProgress, subscribeFlag);
        return groupPurchaseItemExtMapper.update(null, wrapper) > 0;
    }
}
