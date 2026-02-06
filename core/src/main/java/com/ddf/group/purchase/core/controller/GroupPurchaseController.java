package com.ddf.group.purchase.core.controller;

import com.ddf.boot.common.api.model.common.response.PageResult;
import com.ddf.boot.common.api.model.common.response.ResponseData;
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
import com.ddf.group.purchase.api.response.group.MyJoinGroupPageResponse;
import com.ddf.group.purchase.api.response.group.OrderDetailResponse;
import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>团购团购管理</p >
 *
 * @menu 团购管理
 * @author Snowball
 * @version 1.0
 * @date 2022/05/30 23:28
 */
@RestController
@RequestMapping("/groupPurchase")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class GroupPurchaseController {

    private final GroupPurchaseApplicationService groupPurchaseApplicationService;

    /**
     * 根据微信接龙文本创建团购信息
     *
     * @param request
     */
    @PostMapping("createFromWxJieLong")
    public ResponseData<Void> createFromWxJieLong(@RequestBody @Validated CreateFromWxJieLongRequest request) {
        groupPurchaseApplicationService.createFromWxJieLong(request);
        return ResponseData.empty();
    }

    /**
     * 自定义文本创建团购信息
     *
     * @param request
     */
    @PostMapping("customizeCreate")
    public ResponseData<Void> customizeCreate(@RequestBody @Validated CustomizeCreateRequest request) {
        groupPurchaseApplicationService.customizeCreate(request);
        return ResponseData.empty();
    }

    /**
     * 修改我创建的团购信息
     *
     * @param request
     */
    @PostMapping("modifyGroupInfo")
    public ResponseData<Void> modifyGroupInfo(@RequestBody @Validated ModifyGroupRequest request) {
        groupPurchaseApplicationService.modifyGroupInfo(request);
        return ResponseData.empty();
    }


    /**
     * 更新团购状态
     *
     * @param request
     * @return
     */
    @PostMapping("updateGroupStatus")
    public ResponseData<Boolean> updateGroupStatus(@RequestBody @Validated UpdateGroupStatusRequest request) {
        return ResponseData.success(groupPurchaseApplicationService.updateGroupStatus(request));
    }


    /**
     * 团购市场
     *
     * @param request
     * @return
     */
    @GetMapping("marketplaceGroupPageList")
    public ResponseData<PageResult<MarketplaceGroupPurchaseListResponse>> marketplaceGroupPageList(@Validated MyInitiatedGroupPageRequest request) {
        return ResponseData.success(groupPurchaseApplicationService.marketplaceGroupPageList(request));
    }

    /**
     * 我发起的团购
     *
     * @param request
     * @return
     */
    @GetMapping("myInitiatedGroup")
    public ResponseData<PageResult<GroupPurchaseListResponse>> myInitiatedGroup(@Validated MyInitiatedGroupPageRequest request) {
        return ResponseData.success(groupPurchaseApplicationService.myInitiatedGroup(request));
    }

    /**
     * 我参与的团购
     *
     * @param request
     * @return
     */
    @GetMapping("myJoinGroup")
    public ResponseData<PageResult<MyJoinGroupPageResponse>> myJoinGroup(@Validated MyJoinGroupPageRequest request) {
        return ResponseData.success(groupPurchaseApplicationService.myJoinGroup(request));
    }

    /**
     * 发布团购
     *
     * @param request
     * @return
     */
    @PostMapping("setPublicState")
    public ResponseData<Boolean> publishGroup(@RequestBody @Validated PublishGroupRequest request) {
        return ResponseData.success(groupPurchaseApplicationService.publishGroup(request));
    }

    /**
     * 团购明细
     *
     * @param groupId
     * @return
     */
    @GetMapping("groupDetail")
    public ResponseData<GroupPurchaseListResponse> groupDetail(@RequestParam Long groupId) {
        return ResponseData.success(groupPurchaseApplicationService.groupDetail(groupId));
    }

    /**
     * 参团
     *
     * @param request
     * @return
     */
    @PostMapping("join")
    public ResponseData<Void> join(@RequestBody @Validated JoinGroupRequest request) {
        groupPurchaseApplicationService.join(request);
        return ResponseData.empty();
    }

    /**
     * 查看参团明细
     *
     * @param groupId
     * @return
     */
    @GetMapping("joinInfo")
    public ResponseData<List<GroupItemResponse>> joinInfo(@RequestParam Long groupId) {
        return ResponseData.success(groupPurchaseApplicationService.joinInfo(groupId));
    }

    /**
     * 订阅/取消订阅团购信息
     *
     * @param request
     * @return
     */
    @PostMapping("setSubscribeState")
    public ResponseData<Boolean> subscribeGroup(@RequestBody @Validated SubscribeGroupRequest request) {
        return ResponseData.success(groupPurchaseApplicationService.subscribeGroup(request));
    }

    /**
     * 模拟支付
     *
     * @param request
     * @return
     */
    @PostMapping("simulationPay")
    public ResponseData<Boolean> simulationPay(@RequestBody @Validated SimulationPayRequest request) {
        return ResponseData.success(groupPurchaseApplicationService.simulationPay(request));
    }

    /**
     * 订单详情
     *
     * @param joinItemId
     * @return
     */
    @GetMapping("orderDetail")
    public ResponseData<OrderDetailResponse> orderDetail(@RequestParam Long joinItemId) {
        return ResponseData.success(groupPurchaseApplicationService.orderDetail(joinItemId));
    }

    /**
     * 团购状态跟踪查询
     *
     * @param groupId
     * @return
     */
    @GetMapping("listGroupTrace")
    public ResponseData<List<GroupPurchaseTraceDTO>> listGroupTrace(@RequestParam Long groupId) {
        return ResponseData.success(groupPurchaseApplicationService.listGroupTrace(groupId));
    }
}
