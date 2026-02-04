package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.api.request.group.AddressDomain;
import com.ddf.group.purchase.api.response.group.GroupItemResponse;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.api.response.group.OrderDetailResponse;
import com.ddf.group.purchase.core.model.cqrs.group.MyJoinGroupQuery;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2026/02/04 19:18
 */
public interface GroupPurchaseItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupPurchaseItem record);

    int insertSelective(GroupPurchaseItem record);

    GroupPurchaseItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupPurchaseItem record);

    int updateByPrimaryKey(GroupPurchaseItem record);


    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int batchInsert(List<GroupPurchaseItem> list);


    /**
     * 查询团购下的参团用户id集合
     *
     * @param groupId
     * @return
     */
    List<Long> selectJoinPaidUids(@Param("groupId") Long groupId);

    /**
     * 查询参团明细（商品和明细一对一）
     *
     * @param groupId
     * @return
     */
    List<GroupItemResponse> listGroupPaidItem(@Param("groupId") Long groupId);

    /**
     * 我的参团列表查询
     *
     * @param query
     * @return
     */
    List<MyJoinGroupPageResponse> myJoinGroup(@Param("query") MyJoinGroupQuery query);

    /**
     * 更新状态为已支付
     *
     * @param id
     * @param addressDomain
     * @param remark
     * @return
     */
    int updatePaid(@Param("id") Long id, @Param("address") AddressDomain addressDomain, @Param("remark") String remark);

    /**
     * 查询订单详细
     *
     * @param id
     * @return
     */
    OrderDetailResponse selectOrderDetail(@Param("id") Long id);
}
