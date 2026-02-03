package com.ddf.group.purchase.core.repository;

import com.ddf.group.purchase.core.mapper.GroupPurchaseItemGoodMapper;
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

    private final GroupPurchaseItemGoodMapper groupPurchaseItemGoodMapper;

    /**
     * 根据参团记录ID查询商品列表
     *
     * @param groupItemId
     * @return
     */
    public List<GroupPurchaseItemGood> selectByGroupPurchaseItemId(Long groupItemId) {
        return groupPurchaseItemGoodMapper.selectByGroupPurchaseItemId(groupItemId);
    }


    /**
     * 查找用户指定团购和商品的参团信息
     *
     * @param groupId
     * @param userId
     * @param goodId
     * @return
     */
    public GroupPurchaseItemGood selectUserGroupGood(Long groupId, Long userId, Long goodId) {
        return groupPurchaseItemGoodMapper.selectUserGroupGood(groupId, userId, goodId);
    }

    /**
     * 批量插入
     *
     * @param list
     */
    public void batchInsert(List<GroupPurchaseItemGood> list) {
        groupPurchaseItemGoodMapper.batchInsert(list);
    }

    /**
     * 查找指定用户指定团购的购买商品列表
     *
     * @param groupId
     * @param userId
     * @return
     */
    public List<GroupPurchaseItemGood> listUserGroupGood(Long groupId, Long userId) {
        return groupPurchaseItemGoodMapper.listUserGroupGood(groupId, userId);
    }
}
