package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.core.model.entity.GroupPurchaseTrace;
import java.util.List;

/**
* <p>description</p >
*
* @author Snowball
* @version 1.0
* @date 2022/07/14 18:13
*/
public interface GroupPurchaseTraceMapper {

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
     * 插入
     *
     * @param groupPurchaseTrace
     * @return
     */
    int insert(GroupPurchaseTrace groupPurchaseTrace);

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
