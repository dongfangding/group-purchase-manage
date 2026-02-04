package com.ddf.group.purchase.core.repository;

import com.ddf.group.purchase.core.mapper.GroupPurchaseGoodMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseGood;
import java.util.List;
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

    private final GroupPurchaseGoodMapper groupPurchaseGoodMapper;

    /**
     * 根据团购主表获取团购商品信息
     *
     * @param groupId
     * @return
     */
    public GroupPurchaseGood getByGroupId(Long groupId) {
        return groupPurchaseGoodMapper.selectByGroupPurchaseId(groupId);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public GroupPurchaseGood getById(Long id) {
        return groupPurchaseGoodMapper.selectByPrimaryKey(id);
    }

    /**
     * 插入
     *
     * @param groupPurchaseGood
     * @return
     */
    public int insert(GroupPurchaseGood groupPurchaseGood) {
        return groupPurchaseGoodMapper.insert(groupPurchaseGood);
    }

    /**
     * 扣减库存
     *
     * @param id
     * @param reduceStock
     * @return
     */
    public int reduceGoodStock(Long id, Integer reduceStock) {
        return groupPurchaseGoodMapper.reduceGoodStock(id, reduceStock);
    }
}
