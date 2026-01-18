package com.ddf.group.purchase.core.mapper;

import com.ddf.group.purchase.api.request.group.AddressDomain;
import com.ddf.group.purchase.api.response.group.GroupItemResponse;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.api.response.group.OrderDetailResponse;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
    List<MyJoinGroupPageResponse> myJoinGroup(@Param("query") com.ddf.group.purchase.core.model.cqrs.group.MyJoinGroupQuery query);

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
