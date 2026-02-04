package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseTrace;
import java.util.List;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/04 19:16
 */
public interface GroupPurchaseTraceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseTrace record);

    int insertSelective(GroupPurchaseTrace record);

    GroupPurchaseTrace selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseTrace record);

    int updateByPrimaryKey(GroupPurchaseTrace record);

    /**
     * 根据团购ID查询状态跟踪列表
     *
     * @param groupPurchaseId
     * @return
     */
    List<GroupPurchaseTrace> selectByGroupPurchaseId(Long groupPurchaseId);
}
