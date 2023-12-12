package com.deyi.daxie.cloud.operation.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deyi.daxie.cloud.operation.domain.JobDetail;
import com.deyi.daxie.cloud.operation.file.FileService;
import com.deyi.daxie.cloud.operation.mapper.JobDetailMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("taskJob")
public class TaskJob {
    @Resource
    private TaskService taskService;
    @Resource
    private FileService fileService;
    @Resource
    private JobDetailMapper jobDetailMapper;
    @Scheduled(cron = "0 50 20 * * ?")
    public void emailJob() {
        taskService.work("emailJob");
        System.out.println("通过cron定义的定时任务");
    }
    @Scheduled(cron = "0 50 19 * * ?")
    public void uploadJob() {
        List<JobDetail> wd =getWrapper("uploadJob");
        for(JobDetail jobDetail:wd){
            //SplitFile.splitDataToSaveFile(10000,jobDetail.getDict());
            fileService.parseFile(jobDetail.getDict(),jobDetail.getFlag(),jobDetail.getDeviceNum());
        }
        System.out.println("通过cron定义的定时任务");
    }
    @Scheduled(cron = "0 30 22 * * ?")
    public void deleteJob() {
        taskService.dropData();
        System.out.println("通过cron定义的定时任务");
    }
    public List<JobDetail> getWrapper(String keyword){
        QueryWrapper<JobDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("name", keyword);
        List<JobDetail> wd = jobDetailMapper.selectList(wrapper);
        return wd;
    }
}
