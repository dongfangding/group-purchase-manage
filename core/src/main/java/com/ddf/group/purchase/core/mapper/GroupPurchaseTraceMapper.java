package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseTrace;
import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/03 23:45
 */
public interface GroupPurchaseTraceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseTrace record);

    int insertSelective(GroupPurchaseTrace record);

    GroupPurchaseTrace selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseTrace record);

    int updateByPrimaryKey(GroupPurchaseTrace record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    GroupPurchaseTrace selectById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<GroupPurchaseTrace> selectList();

    /**
     * 根据团购ID查询状态跟踪列表
     *
     * @param groupPurchaseId
     * @return
     */
    List<GroupPurchaseTrace> selectByGroupPurchaseId(Long groupPurchaseId);

    /**
     * 根据ID更新
     *
     * @param groupPurchaseTrace
     * @return
     */
    int updateById(GroupPurchaseTrace groupPurchaseTrace);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}