package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseGoodExtMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseGood;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>团购商品仓储</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/25 19:59
 */
@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GroupPurchaseGoodRepository {

    private final GroupPurchaseGoodExtMapper groupPurchaseGoodExtMapper;

    /**
     * 根据团购主表获取团购商品信息
     *
     * @param groupId
     * @return
     */
    public GroupPurchaseGood getByGroupId(Long groupId) {
        final LambdaQueryWrapper<GroupPurchaseGood> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GroupPurchaseGood::getGroupPurchaseId, groupId);
        return groupPurchaseGoodExtMapper.selectOne(wrapper);
    }
}
