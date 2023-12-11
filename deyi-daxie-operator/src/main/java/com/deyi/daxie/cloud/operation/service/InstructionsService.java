package com.deyi.daxie.cloud.operation.service;

import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.operation.domain.dto.TcsGetInstructionsDto;
import com.deyi.daxie.cloud.operation.domain.vo.TcsGetInstructionsVo;

import javax.servlet.http.HttpServletResponse;


public interface InstructionsService {

    TableDataInfo<TcsGetInstructionsDto> list(TcsGetInstructionsVo vo);

    void export(TcsGetInstructionsVo vo, HttpServletResponse response);
}
