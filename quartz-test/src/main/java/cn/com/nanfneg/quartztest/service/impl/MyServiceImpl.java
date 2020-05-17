package cn.com.nanfneg.quartztest.service.impl;

import cn.com.nanfneg.quartztest.conf.MyJob;
import cn.com.nanfneg.quartztest.service.IMyService;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-05 20:21
 */
@Service
public class MyServiceImpl implements IMyService {

    private static final String JOB_GROUP_NAME = "quartz";

    private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

    @Resource
    private Scheduler scheduler;

    @Override
    public String helloWorld() {
        logger.info(">>>>>>>>>>>>>>>>hello world<<<<<<<<<<<<<<<");
        String strategy = "0 * * * * ?";
        String name  = String.valueOf(System.currentTimeMillis());
        addJob(strategy,name);
        return "hello world";
    }

    private void addJob(String strategy,String name){
        JobDataMap jobDataMap = new JobDataMap();
        Set<Trigger> triggerSet = new HashSet<>();
        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setStartTime(new Date());
        try {
            trigger.setCronExpression(strategy);
        }catch (ParseException e){
            e.printStackTrace();
            throw new RuntimeException("cron表达式异常");
        }
        trigger.setName(name);
        triggerSet.add(trigger);

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity(name, JOB_GROUP_NAME)
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        }catch (SchedulerException e){
            e.printStackTrace();
            throw new RuntimeException("巡检任务停用失败,请刷新后重试");
        }
    }
}
