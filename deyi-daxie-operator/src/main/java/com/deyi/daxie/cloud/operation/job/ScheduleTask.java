package com.deyi.daxie.cloud.operation.job;

public class ScheduleTask implements Runnable{
    private static final int TIMEOUT = 30000;

    private String id;
    private TaskService service;
    private String keyword;

    public String getId() {
        return id;
    }

    /**
     * @param id      任务ID
     * @param service 业务类
     * @param keyword 关键字参数
     */
    public ScheduleTask(String id, TaskService service, String keyword) {
        this.id = id;
        this.service = service;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        service.work(keyword);
    }
}
