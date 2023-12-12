package com.deyi.daxie.cloud.operation.email;

import com.deyi.daxie.cloud.operation.domain.VelWarnData;
import com.deyi.daxie.cloud.operation.service.WarnDataService;
import com.deyi.daxie.cloud.operation.service.WarnInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Resource
    private EmailService testService;
    @Resource
    private WarnInfoService warnInfoService;

    @PostMapping("/send")
    public void test(String fileDir,String path,String deptName,int flag) {
        List<VelWarnData> warn = warnInfoService.queryByTime();
        testService.createAlertInfo(warn,fileDir,path,deptName,flag);
    }
}
