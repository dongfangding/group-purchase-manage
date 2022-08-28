package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseItemGoodExtMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItemGood;
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
public class GroupPurchaseItemGoodRepository {

    private final GroupPurchaseItemGoodExtMapper groupPurchaseItemGoodExtMapper;


    /**
     * 查找用户指定团购和商品的参团信息
     *
     * @param groupId
     * @param userId
     * @param goodId
     * @return
     */
    public GroupPurchaseItemGood selectUserGroupGood(Long groupId, Long userId, Long goodId) {
        final LambdaQueryWrapper<GroupPurchaseItemGood> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GroupPurchaseItemGood::getGroupPurchaseId, groupId)
                .eq(GroupPurchaseItemGood::getJoinUid, userId)
                .eq(GroupPurchaseItemGood::getGroupPurchaseGoodId, goodId);
        return groupPurchaseItemGoodExtMapper.selectOne(wrapper);
    }

    /**
     * 查找指定用户指定团购的购买商品列表
     *
     * @param groupId
     * @param userId
     * @return
     */
    public List<GroupPurchaseItemGood> listUserGroupGood(Long groupId, Long userId) {
        final LambdaQueryWrapper<GroupPurchaseItemGood> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GroupPurchaseItemGood::getGroupPurchaseId, groupId)
                .eq(GroupPurchaseItemGood::getJoinUid, userId);
        return groupPurchaseItemGoodExtMapper.selectList(wrapper);
    }
}
