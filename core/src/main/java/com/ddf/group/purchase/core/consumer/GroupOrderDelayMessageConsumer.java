package com.ddf.group.purchase.core.consumer;

import com.ddf.group.purchase.core.application.GroupPurchaseApplicationService;
import com.ddf.group.purchase.core.constants.RocketMQConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>订单延迟消息</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/16 14:05
 */
@Component
@RocketMQMessageListener(topic = RocketMQConst.Topic.DELAY_TOPIC, consumeMode = ConsumeMode.CONCURRENTLY,
        consumerGroup = RocketMQConst.GroupOrder.GROUP,
        selectorExpression = RocketMQConst.GroupOrder.TAG)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class GroupOrderDelayMessageConsumer implements RocketMQListener<Long> {

    private final GroupPurchaseApplicationService groupPurchaseApplicationService;

    @Override
    public void onMessage(Long groupItemId) {
        log.info("[订单关闭延迟消息]接收到关闭订单检查， groupItemId = {}, ", groupItemId);
        try {
            final boolean result = groupPurchaseApplicationService.closeOrder(groupItemId);
            log.info("[订单关闭延迟消息]订单关闭结果， groupItemId = {}, result = {}", groupItemId, result);
        } catch (Exception e) {
            log.error("[订单关闭延迟消息]处理失败， groupItemId = {}", groupItemId, e);
            throw e;
        }
    }
}
