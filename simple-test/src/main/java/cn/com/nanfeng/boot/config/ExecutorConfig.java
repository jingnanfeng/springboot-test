package cn.com.nanfeng.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-06-11 16:04
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 线程池维护线程的最小数量
     */
    private static final int CODE_POOL_SIZE = 10;

    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 50;

    /**
     * 缓存队列
     */
    private static final int QUEUE_CAPACITY = 10;

    /**
     * 允许的空闲时间
     */
    private static final int KEEP_ALIVE = 60;

    @Bean("executorService")
    public ExecutorService executorPool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CODE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix("executor-");
        //设置淘汰策略，如果添加到线程失败，那么主线程会自己去执行该任务，不会等到线程池中的线程去执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(KEEP_ALIVE);
        executor.initialize();
        return executor.getThreadPoolExecutor();
    }


}
