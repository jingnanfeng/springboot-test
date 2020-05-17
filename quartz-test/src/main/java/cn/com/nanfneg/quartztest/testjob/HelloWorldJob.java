package cn.com.nanfneg.quartztest.testjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-04-23 15:41
 */
public class HelloWorldJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello world!:" + jobExecutionContext.getJobDetail().getKey());
    }
}
