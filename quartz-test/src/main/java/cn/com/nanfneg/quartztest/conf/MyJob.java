package cn.com.nanfneg.quartztest.conf;

import cn.com.nanfneg.quartztest.service.IMyService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-05 20:19
 */
public class MyJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(MyJob.class);

    @Resource
    private IMyService myService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        myService.helloWorld();
        logger.info("HI:[{}]",context.getJobDetail().getKey());
    }
}
