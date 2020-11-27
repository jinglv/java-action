package com.demo.action.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author jingLv
 * @date 2020/11/27
 */
@Slf4j
@Configuration
@EnableAsync
public class ExecutorConfig {
    /**
     * 导出服务线程池
     *
     * @return
     */
    @Bean("exportServiceExecutor")
    public Executor exportServiceExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数量，：当前机器的核心数
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        // 队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        // 设置线程池中线程名前缀
        executor.setThreadNamePrefix("export--");
        // 设置拒绝策略：直接拒绝
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
