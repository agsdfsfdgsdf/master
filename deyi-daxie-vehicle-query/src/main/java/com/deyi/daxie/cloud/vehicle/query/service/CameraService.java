package com.deyi.daxie.cloud.vehicle.query.service;

import com.deyi.daxie.cloud.vehicle.query.util.Result;
import com.deyi.daxie.cloud.vehicle.query.vo.CameraInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.TableDataInfo;

/**
 * Description: 摄像机接口
 * @date 2022/9/25
 * @author Huang ShuYing
 */
public interface CameraService {
    /**
     * Description:
     * @param pageSize 开始位置
     * @param pageNum 结束位置
     * @param cameraId 摄像机id
     * @param vin 车id
     * @param cameraPosition 摄像机位置
     * @date 2022/9/25
     * @return List<CameraInfo>
     * @author Huang ShuYing
     */
    TableDataInfo list(int pageSize, int pageNum, String cameraId, String vin, String cameraPosition);

    /**
     * Description:
     * @param cameraId 摄像机id
     * @return VehicleInfo
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    Result detail(String cameraId);
    /**
     * Description:
     * @param cameraInfo 摄像机
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    Result add(CameraInfo cameraInfo);
    /**
     * Description:
     * @param cameraInfo 摄像机
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    Result edit(CameraInfo cameraInfo);
    /**
     * Description:
     * @param cameraId 摄像机id
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    Result remove(String cameraId);
    /**
     * Description: 定时更新摄像头授权码
     * @date 2022/10/25
     * @author Huang ShuYing
     */
    void updateAccess();
}
