package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseGood;
import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/13 18:45
 */
public interface GroupPurchaseGoodMapper {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    GroupPurchaseGood selectById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<GroupPurchaseGood> selectList();

    /**
     * 根据团购ID查询商品列表
     *
     * @param groupPurchaseId
     * @return
     */
    List<GroupPurchaseGood> selectByGroupPurchaseId(Long groupPurchaseId);

    /**
     * 插入
     *
     * @param groupPurchaseGood
     * @return
     */
    int insert(GroupPurchaseGood groupPurchaseGood);

    /**
     * 根据ID更新
     *
     * @param groupPurchaseGood
     * @return
     */
    int updateById(GroupPurchaseGood groupPurchaseGood);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}
