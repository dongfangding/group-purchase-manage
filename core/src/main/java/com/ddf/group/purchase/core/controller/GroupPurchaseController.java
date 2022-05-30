package com.ddf.group.purchase.core.controller;

import com.ddf.group.purchase.api.request.group.CreateFromWxJieLongRequest;
import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/common")
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
}
