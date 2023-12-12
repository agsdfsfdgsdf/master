package com.deyi.daxie.cloud.operation.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;

    /**
     * 新增或者修改
     *
     * @param id      任务ID
     * @param cron    执行时间表达式
     * @param keyword 关键字参数
     * @return true or false
     */
    @PostMapping("/test")
    public boolean saveOrEdit(@RequestParam("id") String id,
                              @RequestParam("cron") String cron,
                              @RequestParam("keyword") String keyword) {
        return ScheduleUtil.reset(new ScheduleTask(id, service, keyword), cron);
    }
}
