package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseItem;
import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/11/25 11:44
 */
public interface GroupPurchaseItemMapper {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    GroupPurchaseItem selectById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<GroupPurchaseItem> selectList();

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(List<GroupPurchaseItem> list);

    /**
     * 根据ID更新
     *
     * @param groupPurchaseItem
     * @return
     */
    int updateById(GroupPurchaseItem groupPurchaseItem);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}
