package com.deyi.daxie.cloud.operation.service;


import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.operation.domain.ControlData;
import com.deyi.daxie.cloud.operation.domain.dto.ControlDataDto;
import com.deyi.daxie.cloud.operation.domain.vo.ControlDataVo;

public interface ControlDataService {

    TableDataInfo<ControlDataDto> list(ControlDataVo vo);

    void export(ControlDataVo vo, HttpServletResponse response);

    ControlDataDto detail(Long id);

    /**
     * 根据车号列表获取车辆位置信息集合
     * @param deviceNum 车号列表
     * @return 车辆位置信息集合
     */
    List<ControlData> getLocation(Set<String> deviceNum);

    /**
     * 获取指定时间区间的数据列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 数据列表
     */
    List<ControlData> getByRange(Date startTime, Date endTime);
}
