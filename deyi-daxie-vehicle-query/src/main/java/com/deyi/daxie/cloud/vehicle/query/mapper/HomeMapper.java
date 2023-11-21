package com.deyi.daxie.cloud.vehicle.query.mapper;

import com.deyi.daxie.cloud.vehicle.query.vo.TaskInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * @date 2022/9/25
 * @author Huang ShuYing
 */
@Mapper
public interface HomeMapper {

    /**
     * Description:
     * @param time 时间
     * @return 返回个数
     * @date 2022/10/13
     * @author Huang ShuYing
     */
    List<TaskInfo> taskList(@Param("time") String time);
}
