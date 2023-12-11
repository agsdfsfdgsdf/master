package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.TcsGetInstructionsDto;
import com.deyi.daxie.cloud.operation.domain.vo.TcsGetInstructionsVo;
import com.deyi.daxie.cloud.operation.service.InstructionsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/instruct")
public class InstructionsController {
    @Autowired
    private InstructionsService instructionsService;
    @PostMapping("/list")
    @ApiOperation("分页列表")
    @Log(title = "最新指令数据->分页列表", businessType = BusinessType.SEARCH)
    public TableDataInfo<TcsGetInstructionsDto> list(TcsGetInstructionsVo vo){
        return instructionsService.list(vo);
    }
    @GetMapping("/export")
    @ApiOperation("c")
    @Log(title = "最新指令数据->最新指令列表", businessType = BusinessType.EXPORT)
    public void export(TcsGetInstructionsVo vo, HttpServletResponse response){
        instructionsService.export(vo, response);
    }
}
