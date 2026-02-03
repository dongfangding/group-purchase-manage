package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseItemGood;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/03 23:45
 */
public interface GroupPurchaseItemGoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseItemGood record);

    int insertSelective(GroupPurchaseItemGood record);

    GroupPurchaseItemGood selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseItemGood record);

    int updateByPrimaryKey(GroupPurchaseItemGood record);

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

    /**
     * 查找用户指定团购和商品的参团信息
     *
     * @param groupId
     * @param userId
     * @param goodId
     * @return
     */
    GroupPurchaseItemGood selectUserGroupGood(@Param("groupId") Long groupId, @Param("userId") Long userId,
            @Param("goodId") Long goodId);

    /**
     * 查找指定用户指定团购的购买商品列表
     *
     * @param groupId
     * @param userId
     * @return
     */
    List<GroupPurchaseItemGood> listUserGroupGood(@Param("groupId") Long groupId, @Param("userId") Long userId);
}
