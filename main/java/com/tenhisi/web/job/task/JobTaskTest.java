package com.tenhisi.web.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务
 * @author Shane
 * @date 2021-03-07 22:34
 */
@Component("jobTaskTest")
public class JobTaskTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 有参调用方法
	 * @author Shane
	 * @date 2021-03-07 22:40
	 */
	public void fastParams(String params) {
		System.out.println("==================///////////测试定时任务开始////////////////=====================");
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		System.out.println("==================///////////测试定时任务完成////////////////=====================");
	}
	
	/**
	 * 无参调用方法
	 * @author Shane
	 * @date 2021-03-07 22:44
	 * return
	 */
	public void fastNoParms() {
		logger.info("我是不带参数的test2方法，正在被执行");
	}
	
}
