package cn.com.nanfeng.scheduletest.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liutao
 * @Title ScheduledTask
 * @Description 调度任务
 * @date 2020-05-11 11:50
 */
@Component
public class ScheduledTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 每10s执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledTaskByCorn(){
        LOGGER.info("定时任务开始 ByCorn:[{}]", sdf.format(new Date()));
        scheduledTask();
        LOGGER.info("定时任务结束 ByCorn:[{}]",sdf.format(new Date()));

    }

    /**
     * 每10s执行一次
     */
    @Scheduled(fixedRate = 10000)
    public void schedulerTaskByFixedRate(){
        LOGGER.info("定时任务开始 ByFixedRate:[{}]",sdf.format(new Date()));
        scheduledTask();
        LOGGER.info("定时任务结束 ByFixedRate:[{}]",sdf.format(new Date()));
    }

    @Scheduled(fixedDelay = 10000)
    public void schedulerTaskByFixedDelay(){
        LOGGER.info("定时任务开始 ByFixedDelay:[{}]",sdf.format(new Date()));
        scheduledTask();
        LOGGER.info("定时任务结束 ByFixedDelay:[{}]",sdf.format(new Date()));
    }

    private void scheduledTask(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
