package com.deyi.daxie.cloud.vehicle.query.service;

import com.deyi.daxie.cloud.vehicle.query.vo.AccessInfo;
import java.util.List;

/**
 * Description:
 * @date 2022/9/25
 * @author Huang ShuYing
 */
public interface CameraAccessService {
    /**
     * Description:
     * @date 2022/9/25
     * @return List<CameraInfo>
     * @author Huang ShuYing
     */
    List<AccessInfo> list();

}
