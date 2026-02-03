package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseGood;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/03 23:45
 */
public interface GroupPurchaseGoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseGood record);

    int insertSelective(GroupPurchaseGood record);

    GroupPurchaseGood selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseGood record);

    int updateByPrimaryKey(GroupPurchaseGood record);

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
    GroupPurchaseGood selectByGroupPurchaseId(Long groupPurchaseId);

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

    /**
     * 扣减商品库存
     *
     * @param id
     * @param reduceStock
     * @return
     */
    int reduceGoodStock(@Param("id") Long id, @Param("reduceStock") Integer reduceStock);
}