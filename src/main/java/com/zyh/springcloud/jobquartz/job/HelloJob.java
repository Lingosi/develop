package com.zyh.springcloud.jobquartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloJob implements BaseJob{
	private static Logger logger = LoggerFactory.getLogger(HelloJob.class);
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("我执行了.........***********************");
		JobDetail jd = context.getJobDetail();
		String name = jd.getClass().getName();
		logger.info("加点儿日志吧");
		Date tmpNow = new Date(context.getJobRunTime());
		context.getNextFireTime();
		logger.info("在执行了..........，名称：{}，当前时间：{}， firetime：{}，next：{}", name, sdfTime.format(tmpNow), sdfTime.format(context.getFireTime()), sdfTime.format(context.getNextFireTime()));
	}

}
