package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseGood;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/04 19:16
 */
public interface GroupPurchaseGoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseGood record);

    int insertSelective(GroupPurchaseGood record);

    GroupPurchaseGood selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseGood record);

    int updateByPrimaryKey(GroupPurchaseGood record);

    /**
     * 根据团购ID查询商品列表
     *
     * @param groupPurchaseId
     * @return
     */
    GroupPurchaseGood selectByGroupPurchaseId(Long groupPurchaseId);


    /**
     * 扣减商品库存
     *
     * @param id
     * @param reduceStock
     * @return
     */
    int reduceGoodStock(@Param("id") Long id, @Param("reduceStock") Integer reduceStock);
}
