package com.ddf.group.purchase.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ddf.group.purchase.core.mapper.ext.GroupPurchaseItemExtMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItem;
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
}
