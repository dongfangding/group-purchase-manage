package com.ddf.group.purchase.core.application;

import com.ddf.boot.common.api.model.common.PageResult;
import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.api.request.group.CustomizeCreateRequest;
import com.ddf.group.purchase.api.request.group.JoinGroupRequest;
import com.ddf.group.purchase.api.request.group.ModifyGroupRequest;
import com.ddf.group.purchase.api.request.group.MyInitiatedGroupPageRequest;
import com.ddf.group.purchase.api.request.group.MyJoinGroupPageRequest;
import com.ddf.group.purchase.api.request.group.PublishGroupRequest;
import com.ddf.group.purchase.api.request.group.SimulationPayRequest;
import com.ddf.group.purchase.api.request.group.SubscribeGroupRequest;
import com.ddf.group.purchase.api.request.group.UpdateGroupStatusRequest;
import com.ddf.group.purchase.api.response.group.GroupItemResponse;
import com.ddf.group.purchase.api.response.group.GroupPurchaseListResponse;
import com.ddf.group.purchase.api.response.group.GroupPurchaseTraceDTO;
import com.ddf.group.purchase.api.response.group.MarketplaceGroupPurchaseListResponse;
import com.ddf.group.purchase.api.response.group.MyInitiatedGroupPageResponse;
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.api.response.group.OrderDetailResponse;
import com.ddf.group.purchase.core.model.entity.GroupPurchaseInfo;
import java.util.List;

/**
 * <p>团购业务层</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/29 23:16
 */
public interface GroupPurchaseApplicationService {

    /**
     * 团购明细
     *
     * @param groupId
     * @return
     */
    GroupPurchaseListResponse groupDetail(Long groupId);

    /**
     * 校验团购信息主表是否可以被用户操作（非团长）
     *
     * @param groupId
     * @return
     */
    GroupPurchaseInfo checkGroupUserCanOperate(Long groupId);

    /**
     * 基于微信接龙文案创建团购信息
     *
     * @param request
     */
    void createFromWxJieLong(CreateFromWxJieLongRequest request);

    /**
     * 自定义文本创建团购信息
     *
     * @param request
     */
    void customizeCreate(CustomizeCreateRequest request);

    /**
     * 修改我创建的团购信息
     *
     * @param request
     */
    void modifyGroupInfo(ModifyGroupRequest request);

    /**
     * 更改团购状态
     *
     * @param request
     * @return
     */
    boolean updateGroupStatus(UpdateGroupStatusRequest request);

    /**
     * 我发起的团购列表查询
     *
     * @param request
     * @return
     */
    PageResult<GroupPurchaseListResponse> myInitiatedGroup(MyInitiatedGroupPageRequest request);

    /**
     * 团购列表查询
     *
     * @param request
     * @return
     */
    PageResult<MyInitiatedGroupPageResponse> pageList(MyInitiatedGroupPageRequest request);

    /**
     * 我参与的团购
     *
     * @param request
     * @return
     */
    PageResult<MyJoinGroupPageResponse> myJoinGroup(MyJoinGroupPageRequest request);


    /**
     * 发布团购
     *
     * @param request
     * @return
     */
    boolean publishGroup(PublishGroupRequest request);

    /**
     * 订阅/取消订阅团购信息
     *
     * @param request
     * @return
     */
    boolean subscribeGroup(SubscribeGroupRequest request);

    /**
     * 市场团购查询
     *
     * @param request
     * @return
     */
    PageResult<MarketplaceGroupPurchaseListResponse> marketplaceGroupPageList(MyInitiatedGroupPageRequest request);

    /**
     * 参团
     *
     * @param request
     */
    void join(JoinGroupRequest request);

    /**
     * 查看指定团购的参团详情
     *
     * @param groupId
     * @return
     */
    List<GroupItemResponse> joinInfo(Long groupId);

    /**
     * 假支付
     * 模拟
     *
     * @param request
     */
    boolean simulationPay(SimulationPayRequest request);

    /**
     * 关闭未支付订单
     *
     * @param groupItemId
     */
    boolean closeOrder(Long groupItemId);

    /**
     * 查询订单详情
     *
     * @param id
     * @return
     */
    OrderDetailResponse orderDetail(Long id);

    /**
     * 团购状态跟踪查询
     *
     * @param groupId
     * @return
     */
    List<GroupPurchaseTraceDTO> listGroupTrace(Long groupId);

}
