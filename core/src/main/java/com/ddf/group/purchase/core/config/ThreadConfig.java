package com.ddf.group.purchase.core.config;

import com.ddf.boot.common.core.helper.ThreadBuilderHelper;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <p>线程池配置类</p >
 *
 * @author Snowball
 * @version 1.0
 * @date 2022/05/28 15:50
 */
@Configuration
public class ThreadConfig {

    /**
     * 发送邮件线程池
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor mailThreadPool() {
        return ThreadBuilderHelper.buildThreadExecutor("mail-thread-pool-", 3600, 1000, 4, 4);
    }

    /**
     * 团购事件监听线程池
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor groupEventThreadPool() {
        return ThreadBuilderHelper.buildThreadExecutor(
                "group-event-thread-pool-", 3600, 1000, Runtime
                        .getRuntime()
                        .availableProcessors(), Runtime
                        .getRuntime()
                        .availableProcessors() * 2, new ThreadPoolExecutor.CallerRunsPolicy(), true, true
        );
    }
}
