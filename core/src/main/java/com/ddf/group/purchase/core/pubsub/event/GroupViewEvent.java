package com.ddf.group.purchase.core.pubsub.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

/**
 * <p>查看团购事件</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/16 18:00
 */
public class GroupViewEvent extends ApplicationEvent {

    @Getter
    private final Body body;

    public GroupViewEvent(Object source, Body body) {
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
