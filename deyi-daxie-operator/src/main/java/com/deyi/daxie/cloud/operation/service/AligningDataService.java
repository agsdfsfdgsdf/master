package com.deyi.daxie.cloud.operation.service;


import javax.servlet.http.HttpServletResponse;

import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.operation.domain.AligningData;
import com.deyi.daxie.cloud.operation.domain.dto.AligningDataDto;
import com.deyi.daxie.cloud.operation.domain.vo.AligningDataVo;

import java.util.Date;
import java.util.List;

public interface AligningDataService {

    TableDataInfo<AligningDataDto> list(AligningDataVo vo);

    void export(AligningDataVo vo, HttpServletResponse response);

    AligningDataDto detail(Long id);

    /**
     * 根据时间区间获取对位数据
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 对位数据列表
     */
    List<AligningData> getByRange(Date startTime, Date endTime);
}
