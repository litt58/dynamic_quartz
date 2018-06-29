package com.jzli.dynamic.quartz;

import com.jzli.dynamic.quartz.job.DynamicJob;
import com.jzli.dynamic.quartz.task.DynamicTask;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/6/29
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：暂时不知道怎么加入线程池，只能使用默认的线程池
 * ========================================================
 */
public class DynamicQuartzTest {
    public static void main(String[] args) throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        //添加定时任务
        for (int i = 1; i < 5; i++) {
            //任务执行封装类
            JobDetail jobDetail = new JobDetail();
            //Id,关联后面的trigger
            jobDetail.setName("id_" + i);
            //具体的任务执行类
            DynamicTask task = new DynamicTask("李金钊-" + i);
            jobDetail.setJobClass(DynamicJob.class);
            jobDetail.getJobDataMap().put("task", task);

            SimpleTrigger trigger = new SimpleTrigger("cron_" + i,
                    null,
                    new Date(),
                    null,
                    SimpleTrigger.REPEAT_INDEFINITELY,
                    30L * 1000L);
            scheduler.scheduleJob(jobDetail, trigger);
        }
        scheduler.start();

    }
}
