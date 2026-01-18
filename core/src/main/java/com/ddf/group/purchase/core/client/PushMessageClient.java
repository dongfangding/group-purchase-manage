package com.ddf.group.purchase.core.client;

import com.ddf.common.boot.mqtt.client.MqttPublishClient;
import com.ddf.common.boot.mqtt.model.request.InnerMqttMessageRequest;
import com.ddf.common.boot.mqtt.model.support.body.MessageBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>description</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/30 15:34
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class PushMessageClient {

    private final MqttPublishClient mqttPublishClient;

    /**
     * 推送消息
     *
     * @param request
     * @param <T>
     */
    public <T extends MessageBody> void publish(InnerMqttMessageRequest request) {
        this.mqttPublishClient.publish(request);
    }
}
