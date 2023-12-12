package com.deyi.daxie.cloud.operation.service;

import com.deyi.daxie.cloud.operation.domain.VelWarnData;
import com.deyi.daxie.cloud.operation.domain.dto.WarnCountDto;
import com.deyi.daxie.cloud.operation.domain.vo.WarnCountVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface WarnInfoService {
    void exportByTime(WarnCountVo vo, HttpServletResponse response);

    List<WarnCountDto> typeCountList(WarnCountVo vo);

    List<VelWarnData> queryByTime();
    Map<String, String> markType();
}
