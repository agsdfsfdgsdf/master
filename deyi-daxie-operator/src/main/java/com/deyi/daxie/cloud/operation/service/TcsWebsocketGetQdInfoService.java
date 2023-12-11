package com.deyi.daxie.cloud.operation.service;

import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.operation.domain.dto.TcsWebsocketGetQdInfoDto;
import com.deyi.daxie.cloud.operation.domain.vo.TcsWebsocketGetQdInfoVo;

import javax.servlet.http.HttpServletResponse;

public interface TcsWebsocketGetQdInfoService {
    TableDataInfo<TcsWebsocketGetQdInfoDto> list(TcsWebsocketGetQdInfoVo vo);

    void export(TcsWebsocketGetQdInfoVo vo, HttpServletResponse response);
}
