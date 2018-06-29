package com.jzli.dynamic.quartz.job;

import com.jzli.dynamic.quartz.task.DynamicTask;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/6/29
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class DynamicJob extends QuartzJobBean {
    private DynamicTask task;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        task.execute();
    }

    public DynamicTask getTask() {
        return task;
    }

    public void setTask(DynamicTask task) {
        this.task = task;
    }
}
