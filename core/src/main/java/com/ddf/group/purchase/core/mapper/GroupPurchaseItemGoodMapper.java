package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseItemGood;
import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/05 19:17
 */
public interface GroupPurchaseItemGoodMapper {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    GroupPurchaseItemGood selectById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<GroupPurchaseItemGood> selectList();

    /**
     * 根据参团记录ID查询商品列表
     *
     * @param groupPurchaseItemId
     * @return
     */
    List<GroupPurchaseItemGood> selectByGroupPurchaseItemId(Long groupPurchaseItemId);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(List<GroupPurchaseItemGood> list);

    /**
     * 插入
     *
     * @param groupPurchaseItemGood
     * @return
     */
    int insert(GroupPurchaseItemGood groupPurchaseItemGood);

    /**
     * 根据ID更新
     *
     * @param groupPurchaseItemGood
     * @return
     */
    int updateById(GroupPurchaseItemGood groupPurchaseItemGood);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}
