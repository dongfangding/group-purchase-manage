package com.ddf.group.purchase.api.request.chat;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>私聊</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/28 18:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivateMessageRequest implements Serializable {

    private static final long serialVersionUID = 3015311890128556988L;

    /**
     * 接收方uid
     */
    private Long toUserId;

    /**
     * 文本内容
     */
    private String text;
}
