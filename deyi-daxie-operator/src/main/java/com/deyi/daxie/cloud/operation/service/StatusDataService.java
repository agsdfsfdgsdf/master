package com.deyi.daxie.cloud.operation.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.operation.domain.StatusData;
import com.deyi.daxie.cloud.operation.domain.dto.StatusDataDto;
import com.deyi.daxie.cloud.operation.domain.vo.StatusDataVo;

public interface StatusDataService {

    TableDataInfo<StatusDataDto> list(StatusDataVo vo);

    void export(StatusDataVo vo, HttpServletResponse response);

    StatusDataDto detail(Long id);

    /**
     * 根据车号列表获取对应车号最后一次信息
     * @param deviceNum 车号
     * @return 信息列表
     */
    List<StatusData> getStatusByList(Set<String> deviceNum);
}
