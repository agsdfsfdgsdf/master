package com.deyi.daxie.cloud.operation.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deyi.daxie.cloud.operation.domain.JobDetail;
import com.deyi.daxie.cloud.operation.domain.OutCar;
import com.deyi.daxie.cloud.operation.domain.OutLane;
import com.deyi.daxie.cloud.operation.domain.VelWarnData;
import com.deyi.daxie.cloud.operation.email.EmailService;
import com.deyi.daxie.cloud.operation.mapper.JobDetailMapper;
import com.deyi.daxie.cloud.operation.mapper.OutCarMapper;
import com.deyi.daxie.cloud.operation.mapper.OutLaneMapper;
import com.deyi.daxie.cloud.operation.service.WarnDataService;
import com.deyi.daxie.cloud.operation.service.WarnInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private EmailService emailService;
    @Resource
    private WarnInfoService warnInfoService;
    @Resource
    private JobDetailMapper jobDetailMapper;
    @Resource
    private OutCarMapper outCarMapper;
    @Resource
    private OutLaneMapper outLaneMapper;


    @Override
    public void work(String keyword) {
        try {
            QueryWrapper<JobDetail> wrapper = new QueryWrapper<>();
            wrapper.eq("name", keyword);
            JobDetail wd = jobDetailMapper.selectOne(wrapper);
            List<VelWarnData> warn = warnInfoService.queryByTime();
            //发送邮件
            emailService.createAlertInfo(warn, wd.getPath(), wd.getDict(), wd.getDeptName(), wd.getFlag());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropData() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        QueryWrapper<OutCar> wrapperOutCar = new QueryWrapper<>();
        wrapperOutCar.like("device_time", sdf.format(date));
        outCarMapper.delete(wrapperOutCar);
        QueryWrapper<OutLane> wrapper = new QueryWrapper<>();
        wrapper.like("device_time", sdf.format(date));
        outLaneMapper.delete(wrapper);
    }
}
