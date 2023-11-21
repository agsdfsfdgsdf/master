package com.deyi.daxie.cloud.vehicle.query.mapper;

import com.deyi.daxie.cloud.vehicle.query.vo.AccessInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 * @date 2022/9/25
 * @author Huang ShuYing
 */
@Mapper
public interface CameraAccessMapper {

    /**
     * Description:
     * @date 2022/9/25
     * @return List<CameraInfo>
     * @author Huang ShuYing
     */
    List<AccessInfo> list();

}
