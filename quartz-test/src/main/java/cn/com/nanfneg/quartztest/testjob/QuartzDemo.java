package cn.com.nanfneg.quartztest.testjob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author liutao
 * @Title 创建JobDetail实例并定义Trigger注册到scheduler,启动scheduler开启调度
 * @Description
 * @date 2020-04-23 15:45
 */
public class QuartzDemo {

    public static void main(String[] args) throws Exception{
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //启动scheduler
        scheduler.start();
        //创建HelloWorldJob的jobDetail实例，并设置name/group
        JobDetail jobDetail = JobBuilder.newJob(HelloWorldJob.class)
                .withIdentity("myJob","myJobGroup")
                //jobDataMap可以给任务传递参数
                .usingJobData("job_param","job_param1")
                .build();
        //创建Trigger触发器设置使用cronSchedule方法调度
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","myTriggerGroup")
                .withIdentity("job_trigger_param","job_trigger_param1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                //.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? 2020"))
                .build();
    }

}
