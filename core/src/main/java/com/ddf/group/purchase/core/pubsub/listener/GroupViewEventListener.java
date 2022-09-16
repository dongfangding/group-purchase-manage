package com.ddf.group.purchase.core.pubsub.listener;

import com.ddf.group.purchase.api.enume.GroupStatisticsTypeEnum;
import com.ddf.group.purchase.core.pubsub.event.GroupViewEvent;
import com.ddf.group.purchase.core.repository.GroupStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <p>监听团购查看事件</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/09/16 18:03
 */
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class GroupViewEventListener implements ApplicationListener<GroupViewEvent> {

    private final GroupStatisticsRepository groupStatisticsRepository;

    @Override
    @Async("groupEventThreadPool")
    public void onApplicationEvent(GroupViewEvent event) {
        final GroupViewEvent.Body body = event.getBody();
        // 增加查看人次，注意用户没去重
        groupStatisticsRepository.incrementStatisticsValue(body.getGroupId(), body.getCurrentUserId(),
                GroupStatisticsTypeEnum.VIEW);
    }
}
