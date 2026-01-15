package com.ddf.group.purchase.core.mapper.ext;

import com.ddf.group.purchase.core.mapper.GroupPurchaseItemGoodMapper;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItemGood;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/28 15:51
 */
public interface GroupPurchaseItemGoodExtMapper extends GroupPurchaseItemGoodMapper {

    /**
     * 根据参团记录ID查询商品列表
     *
     * @param groupPurchaseItemId
     * @return
     */
    List<GroupPurchaseItemGood> selectByGroupPurchaseItemId(@Param("groupPurchaseItemId") Long groupPurchaseItemId);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<GroupPurchaseItemGood> list);
}
