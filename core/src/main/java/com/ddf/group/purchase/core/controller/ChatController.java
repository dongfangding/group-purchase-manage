package com.ddf.group.purchase.core.controller;

import com.ddf.boot.common.api.model.common.response.ResponseData;
import com.ddf.group.purchase.api.request.chat.PrivateMessageRequest;
import com.ddf.group.purchase.core.application.ChatMessageApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>消息类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/28 18:14
 */
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class ChatController {

    private final ChatMessageApplicationService chatMessageApplicationService;


    /**
     * 私聊
     *
     * @param privateMessageRequest
     */
    @PostMapping("/privateMessage")
    public ResponseData<Void> chat(@RequestBody PrivateMessageRequest privateMessageRequest) {
        chatMessageApplicationService.chat(privateMessageRequest);
        return ResponseData.empty();
    }
}
