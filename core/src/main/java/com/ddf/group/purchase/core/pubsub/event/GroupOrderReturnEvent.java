package com.ddf.group.purchase.core.pubsub.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

/**
 * <p>团购订单退款事件</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/16 18:00
 */
public class GroupOrderReturnEvent extends ApplicationEvent {

    @Getter
    private final Body body;

    public GroupOrderReturnEvent(Object source, Body body) {
        super(source);
        this.body = body;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {

        /**
         * 团购id
         */
        private Long groupId;

        /**
         * 当前查看人
         */
        private Long currentUserId;
    }
}
