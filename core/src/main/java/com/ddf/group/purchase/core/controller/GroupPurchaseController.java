package com.ddf.group.purchase.core.controller;

import com.ddf.boot.common.core.model.PageResult;
import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.api.request.group.MyInitiatedGroupPageRequest;
import com.ddf.group.purchase.api.response.group.MyInitiatedGroupPageResponse;
import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void createFromWxJieLong(@RequestBody @Validated CreateFromWxJieLongRequest request) {
        groupPurchaseApplicationService.createFromWxJieLong(request);
    }

    /**
     * 我发起的团购
     *
     * @param request
     * @return
     */
    @GetMapping("myInitiatedGroup")
    public PageResult<MyInitiatedGroupPageResponse> myInitiatedGroup(@Validated MyInitiatedGroupPageRequest request) {
        return groupPurchaseApplicationService.myInitiatedGroup(request);
    }

    /**
     * 我参与的团购
     *
     * @param request
     * @return
     */
    @GetMapping("myJoinGroup")
    public PageResult<MyInitiatedGroupPageResponse> myJoinGroup(@Validated MyInitiatedGroupPageRequest request) {
        return groupPurchaseApplicationService.myJoinGroup(request);
    }

}
