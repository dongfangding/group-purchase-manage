package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.api.response.group.GroupPurchaseListResponse;
import com.ddf.group.purchase.core.model.cqrs.group.GroupListQuery;
import com.ddf.group.purchase.core.model.cqrs.group.MyInitiatedGroupQuery;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/04 19:16
 */
public interface GroupPurchaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseInfo record);

    int insertSelective(GroupPurchaseInfo record);

    GroupPurchaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseInfo record);

    int updateByPrimaryKey(GroupPurchaseInfo record);
    /**
     * 用户发布团购列表查询
     *
     * @param query
     * @return
     */
    List<GroupPurchaseListResponse> list(@Param("query") GroupListQuery query);

    /**
     * 明细
     *
     * @param groupId
     * @return
     */
    GroupPurchaseListResponse details(@Param("groupId") Long groupId);

    /**
     * 条件查询团购列表
     *
     * @param query
     * @return
     */
    List<GroupPurchaseInfo> selectByQuery(@Param("query") MyInitiatedGroupQuery query);
}
