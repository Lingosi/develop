package com.zyh.springcloud.jobquartz.command;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zyh.springcloud.jobquartz.job.BaseJob;


@Component
@Order(value=1)
public class JobCommand implements CommandLineRunner{
	
	private static Logger logger = LoggerFactory.getLogger(JobCommand.class);

	@Autowired 
	@Qualifier("Scheduler")
	private Scheduler scheduler;
	
	static String jobClass = "com.zyh.springcloud.jobquartz.job.HelloJob";
	static String jobGroup = "2";
	static String jobCorn = "0 0/1 * * * ?";
	
	public void run(String... arg0) throws Exception {
		System.out.println("进来了------------------------");
		logger.info("开始处理定时器");
		// TODO Auto-generated method stub
		// 启动调度器  
		scheduler.start(); 
				
		//构建job信息
		JobDetail jobDetail = JobBuilder.newJob(getClass(jobClass).getClass()).withIdentity(jobClass, jobGroup).build();
				
		//表达式调度构建器(即任务执行的时间)
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobCorn);

		//按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClass, jobGroup)
		            .withSchedule(scheduleBuilder).build();
		        
		try {
		    scheduler.scheduleJob(jobDetail, trigger);
		            
		} catch (SchedulerException e) {
			System.out.println("创建定时任务失败"+e);
			logger.error("创建定时器失败：{}", e);
		    throw new Exception("创建定时任务失败");
		}
		logger.info("定时器创建完成");
	}
	
	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob)class1.newInstance();
	}

}
