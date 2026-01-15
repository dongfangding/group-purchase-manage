package com.ddf.group.purchase.core.mapper.ext;

import com.ddf.group.purchase.core.mapper.GroupPurchaseGoodMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseGood;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/20 23:01
 */
public interface GroupPurchaseGoodExtMapper extends GroupPurchaseGoodMapper {

    /**
     * 扣减商品库存
     *
     * @param id
     * @param reduceStock
     * @return
     */
    int reduceGoodStock(@Param("id") Long id, @Param("reduceStock") Integer reduceStock);

    /**
     * 根据团购ID查询商品列表
     *
     * @param groupPurchaseId
     * @return
     */
    List<GroupPurchaseGood> selectByGroupPurchaseId(@Param("groupPurchaseId") Long groupPurchaseId);
}
