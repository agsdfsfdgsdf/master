package com.deyi.daxie.cloud.vehicle.query.mapper;

import com.deyi.daxie.cloud.vehicle.query.vo.CameraInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * @date 2022/9/25
 * @author Huang ShuYing
 */
@Mapper
public interface CameraMapper {

    /**
     * Description:
     * @param start 开始位置
     * @param end 结束位置
     * @param cameraId 摄像机id
     * @param vin 车id
     * @param cameraPosition 摄像机位置
     * @date 2022/9/25
     * @return List<CameraInfo>
     * @author Huang ShuYing
     */
    List<CameraInfo> list(@Param("start") int start, @Param("end") int end, @Param("cameraId")String cameraId, @Param("vin")String vin, @Param("cameraPosition")String cameraPosition);

    /**
     * Description:
     * @param cameraId 摄像机id
     * @param vin 车id
     * @param cameraPosition 摄像机位置
     * @date 2022/9/25
     ** @return int
     * @author Huang ShuYing
     */
    int total(@Param("cameraId")String cameraId, @Param("vin")String vin, @Param("cameraPosition")String cameraPosition);

    /**
     * Description:
     * @param cameraId 摄像机id
     * @return VehicleInfo
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    CameraInfo detail(@Param("cameraId")String cameraId);

    /**
     * Description:
     * @param cameraInfo 摄像机
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    int add(CameraInfo cameraInfo);

    /**
     * Description:
     * @param cameraInfo 摄像机
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    int edit(CameraInfo cameraInfo);

    /**
     * Description:
     * @param cameraId 摄像机id
     * @return int
     * @date 2022/9/25
     * @author Huang ShuYing
     */
    int remove(@Param("cameraId")String cameraId);
    /**
     * Description: 更新摄像机access
     * @param cameraInfo 对象
     * @date 2022/10/25
     * @author Huang ShuYing
     */
    void updateAccess(CameraInfo cameraInfo);
}
