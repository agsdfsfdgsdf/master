package com.deyi.daxie.cloud.operation.job;

public interface TaskService {
    /**
     * 业务处理
     *
     * @param keyword 关键参数
     */
    void work(String keyword);

    void dropData();
}
