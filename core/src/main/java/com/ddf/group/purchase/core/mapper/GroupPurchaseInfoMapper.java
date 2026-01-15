package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/08/20 21:13
 */
public interface GroupPurchaseInfoMapper {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    GroupPurchaseInfo selectById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<GroupPurchaseInfo> selectList();

    /**
     * 插入
     *
     * @param groupPurchaseInfo
     * @return
     */
    int insert(GroupPurchaseInfo groupPurchaseInfo);

    /**
     * 根据ID更新
     *
     * @param groupPurchaseInfo
     * @return
     */
    int updateById(GroupPurchaseInfo groupPurchaseInfo);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}
