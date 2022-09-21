package com.ddf.group.purchase.core.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
* @date 2022/08/28 15:04
*/
public interface GroupPurchaseItemExtMapper extends BaseMapper<GroupPurchaseItem> {

    /**
     * 查询参团明细（商品和明细一对一）
     *
     * @param groupId
     * @return
     */
    List<GroupItemResponse> listGroupItem(@Param("groupId") Long groupId);

    /**
     * 批量插入
     * @param joins
     */
    void batchInsert(@Param("list") List<GroupPurchaseItem> joins);

    /**
     * 我的参团列表查询
     *
     * @param query
     * @return
     */
    List<MyJoinGroupPageResponse> myJoinGroup(@Param("query") MyJoinGroupQuery query);

    /**
     * 查询团购下的参团用户id集合
     *
     * @param groupId
     * @return
     */
    List<Long> selectJoinPaidUids(@Param("groupId") Long groupId);

    /**
     * 更新状态为已支付
     *
     * @param id
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
