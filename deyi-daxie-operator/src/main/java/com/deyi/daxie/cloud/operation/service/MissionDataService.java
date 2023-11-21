package com.deyi.daxie.cloud.operation.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.operation.domain.MissionData;
import com.deyi.daxie.cloud.operation.domain.dto.MissionDataDto;
import com.deyi.daxie.cloud.operation.domain.vo.MissionDataVo;

public interface MissionDataService {

    TableDataInfo<MissionDataDto> list(MissionDataVo vo);

    void export(MissionDataVo vo, HttpServletResponse response);

    MissionDataDto detail(Long id);

    /**
     * 根据车号列表获取最新箱号
     * @param deviceNum 车号列表
     * @return 箱号信息
     */
    List<MissionData> getByDeviceNums(Set<String> deviceNum);

    /**
     * 获取时间区间内的数据列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 数据列表
     */
    List<MissionData> selectByRange(Date startTime, Date endTime);
}
