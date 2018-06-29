package com.jzli.dynamic.quartz.task;

import java.util.Date;

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
public class DynamicTask {
    private String name;

    public DynamicTask(String name) {
        this.name = name;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + "\t" + new Date().toLocaleString() + "\t" + name);
    }
}
