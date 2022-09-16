package com.ddf.group.purchase.core.constants;

/**
 * <p>rocket mq 常量</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/16 13:47
 */
public interface RocketMQConst {

    /**
     * topic
     */
    interface Topic {
        /**
         * 普通topic
         */
        String NORMAL_TOPIC = "group_purchase_normal_topic";
        /**
         * 用来做延迟的topic, 原生倒不是一定存在topic的类型，只是想与普通topic分开，数据隔离而已
         */
        String DELAY_TOPIC = "group_purchase_delay_topic";
    }


    /**
     * 团购订单
     */
    interface GroupOrder {
        String TAG = "group_order_tag";
        String GROUP = "GID_GROUP_ORDER_GROUP";
    }
}
