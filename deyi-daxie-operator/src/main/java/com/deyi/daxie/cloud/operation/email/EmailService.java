package com.deyi.daxie.cloud.operation.email;

import com.deyi.daxie.cloud.operation.domain.VelWarnData;

import java.util.List;

public interface EmailService {
    void createAlertInfo(List<VelWarnData> warnData, String fileDir, String path, String deptName, int flag);
}
