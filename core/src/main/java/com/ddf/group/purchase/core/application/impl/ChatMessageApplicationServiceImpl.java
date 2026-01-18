package com.ddf.group.purchase.core.application.impl;

import com.ddf.boot.common.api.util.JsonUtil;
import com.ddf.common.boot.mqtt.model.request.InnerMqttMessageRequest;
import com.ddf.common.boot.mqtt.model.support.body.TextMessageBody;
import com.ddf.common.boot.mqtt.model.support.header.MqttBaseHeader;
import com.ddf.common.boot.mqtt.model.support.topic.Im2PointMqttTopic;
import com.ddf.group.purchase.api.request.chat.PrivateMessageRequest;
import com.ddf.group.purchase.core.application.ChatMessageApplicationService;
import com.ddf.group.purchase.core.client.PushMessageClient;
import com.ddf.group.purchase.core.client.UserClient;
import com.ddf.group.purchase.core.model.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>聊天类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/30 15:33
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ChatMessageApplicationServiceImpl implements ChatMessageApplicationService {

    private final PushMessageClient pushMessageClient;
    private final UserClient userClient;

    @Override
    public void chat(PrivateMessageRequest request) {
        final UserInfo currentUserInfo = userClient.currentUserInfo();

        // 消息接收对象
        final Im2PointMqttTopic mqttTopic = new Im2PointMqttTopic();
        mqttTopic.setIdentityId(request.getToUserId() + "");

        // 消息头对象
        final MqttBaseHeader header = new MqttBaseHeader();
        header.setSenderId(currentUserInfo.getId() + "");
        header.setSenderName(currentUserInfo.getNickname());
        header.setSourceTimestamp(System.currentTimeMillis());
        header.setSenderAvatar(currentUserInfo.getAvatarUrl());

        // 消息体对象
        final TextMessageBody body = new TextMessageBody();
        body.setMsg(request.getText());
        body.setContentType("text");

        // 最终请求对象
        final InnerMqttMessageRequest messageRequest = new InnerMqttMessageRequest();
        messageRequest.setMessageCode("private_message");
        messageRequest.setDeserializeType("text");
        messageRequest.setTopic(mqttTopic.getFullTopic());
        messageRequest.setHeader(header);
        messageRequest.setBody(JsonUtil.toJson(body));
        // 发送数据
        pushMessageClient.publish(messageRequest);
    }
}
