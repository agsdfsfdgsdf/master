package com.deyi.daxie.cloud.vehicle.query.scheduler;
import com.deyi.daxie.cloud.vehicle.query.service.CameraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description: 定时任务
 * @date 2022/10/25
 * @author Huang ShuYing
 */
@Slf4j
@Component
public class Scheduler {


    @Autowired
    private CameraService cameraService;

    /**
     * Description: 6天一更新
     * @date 2022/10/25
     * @author Huang ShuYing
     */
    @Scheduled(fixedRate = 1000*60*60*24*6)
    public void tasks() {
        cameraService.updateAccess();
    }
}
