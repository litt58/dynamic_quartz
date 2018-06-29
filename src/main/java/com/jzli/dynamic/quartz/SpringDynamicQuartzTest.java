package com.jzli.dynamic.quartz;

import com.jzli.dynamic.quartz.job.DynamicJob;
import com.jzli.dynamic.quartz.task.DynamicTask;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/6/29
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：可以使用自定义线程池
 * ========================================================
 */
public class SpringDynamicQuartzTest {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Scheduler scheduler = (Scheduler) ctx.getBean("scheduler");
        //添加定时任务
        for (int i = 1; i < 2; i++) {
            //任务执行封装类
            JobDetail jobDetail = new JobDetail();
            //Id,关联后面的trigger
            jobDetail.setName("id_" + i);
            //具体的任务执行类
            DynamicTask task = new DynamicTask("李金钊-" + i);
            jobDetail.setJobClass(DynamicJob.class);
            jobDetail.getJobDataMap().put("task", task);
//            scheduler.addJob(jobDetail, true);

            //按照时间间隔运行定时任务
            Trigger trigger = new SimpleTrigger("cron_" + i,
                    null,
                    new Date(),
                    null,
                    SimpleTrigger.REPEAT_INDEFINITELY,
                    30L * 1000L);
            scheduler.scheduleJob(jobDetail, trigger);
            //按照cron表达式运行定时任务
//            CronTrigger cronTrigger = new CronTrigger("cron_" + i, Scheduler.DEFAULT_GROUP, jobDetail.getName(), Scheduler.DEFAULT_GROUP);
//            //时间不等大于60秒，以此类推
//            cronTrigger.setCronExpression("0/90 * * * * ?");
//            scheduler.scheduleJob(cronTrigger);
        }

//        TimeUnit.MINUTES.sleep(1);
//
//        //删除任务
//        for (int i = 1; i < 6; i++) {
//            scheduler.unscheduleJob("cron_" + i, Scheduler.DEFAULT_GROUP);
//        }
    }

}
