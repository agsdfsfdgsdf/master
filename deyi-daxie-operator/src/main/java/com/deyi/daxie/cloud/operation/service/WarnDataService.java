package com.deyi.daxie.cloud.operation.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.operation.domain.WarnData;
import com.deyi.daxie.cloud.operation.domain.dto.WarnDataTypeDto;
import com.deyi.daxie.cloud.operation.domain.vo.WarnDataVo;

public interface WarnDataService {

    TableDataInfo<WarnDataTypeDto> list(WarnDataVo vo);

    void export(WarnDataVo vo, HttpServletResponse response);

    Map<String, String> typeList();

    /**
     * 获取指定时间区间的报警信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 报警信息列表
     */
    List<WarnData> getByRange(Date startTime, Date endTime);
}
