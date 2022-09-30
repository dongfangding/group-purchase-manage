package com.ddf.group.purchase.core.application;

import com.ddf.group.purchase.api.request.chat.PrivateMessageRequest;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/30 15:32
 */
public interface ChatMessageApplicationService {

    /**
     * 单聊
     *
     * @param request
     */
    void chat(PrivateMessageRequest request);

}
