package com.ddf.group.purchase.core.mapper.ext;

import com.ddf.group.purchase.core.mapper.GroupPurchaseGoodMapper;
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
}
