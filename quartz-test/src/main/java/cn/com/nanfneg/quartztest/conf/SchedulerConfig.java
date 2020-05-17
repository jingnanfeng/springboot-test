/*
package cn.com.nanfneg.quartztest.conf;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Component
@Configurable
@EnableConfigurationProperties({QuartzProperties.class})
public class SchedulerConfig {

    @Autowired
    private QuartzProperties quartzProperties;
    @Resource
    private DataSource dataSource;

    @Bean(name="SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException{
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setQuartzProperties(quartzProperties());
        factory.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("org.quartz.scheduler.instanceName", this.quartzProperties.getInstanceName());
        properties.setProperty("org.quartz.scheduler.instanceId", this.quartzProperties.getInstanceId());
        properties.setProperty("org.quartz.scheduler.rmi.export", this.quartzProperties.getExport());
        properties.setProperty("org.quartz.scheduler.rmi.proxy", this.quartzProperties.getProxy());
        properties.setProperty("org.quartz.scheduler.wrapJobExecutionInUserTransaction", this.quartzProperties.getInstanceName());
        properties.setProperty("org.quartz.threadPool.class", this.quartzProperties.getThreadPool());
        properties.setProperty("org.quartz.threadPool.threadCount", this.quartzProperties.getThreadCount());
        properties.setProperty("org.quartz.threadPool.threadPriority", this.quartzProperties.getThreadPriority());
        properties.setProperty("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", this.quartzProperties.getThreadsInheritContextClassLoaderOfInitializingThread());
        properties.setProperty("org.quartz.jobStore.misfireThreshold", this.quartzProperties.getMisfireThreshold());
        properties.setProperty("org.quartz.jobStore.class", this.quartzProperties.getJobStore());
        properties.setProperty("org.quartz.jobStore.driverDelegateClass", this.quartzProperties.getDriverDelegateClass());
        properties.setProperty("org.quartz.jobStore.selectWithLockSQL", this.quartzProperties.getSelectWithLockSQL());
        properties.setProperty("org.quartz.jobStore.tablePrefix", this.quartzProperties.getTablePrefix());
        properties.setProperty("org.quartz.jobStore.isClustered", this.quartzProperties.getIsClustered());
        properties.setProperty("org.quartz.jobStore.useProperties", this.quartzProperties.getUseProperties());
        properties.setProperty("org.quartz.jobStore.clusterCheckinInterval", this.quartzProperties.getClusterCheckinInterval());
        factory.setQuartzProperties(properties);
        factory.setApplicationContextSchedulerContextKey(this.quartzProperties.getApplicationContextSchedulerContextKey());
//        factory.setAutoStartup(this.quartzProperties.getAutoStartup());
//        factory.setOverwriteExistingJobs(this.quartzProperties.getOverwriteExistingJobs());
        factory.setAutoStartup(true);
        factory.setOverwriteExistingJobs(true);
        return factory;
    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }
}


*/
