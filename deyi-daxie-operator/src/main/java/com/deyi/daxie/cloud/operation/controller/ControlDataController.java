package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.ControlDataDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import com.deyi.daxie.cloud.operation.domain.vo.ControlDataVo;
import com.deyi.daxie.cloud.operation.service.ControlDataService;

@RestController
@RequestMapping("control-data")
@Api(tags = "控制数据")
public class ControlDataController {

    @Autowired
    private ControlDataService service;

    @GetMapping
    @ApiOperation("分页列表")
    @Log(title = "控制数据->分页列表", businessType = BusinessType.SEARCH)
    public TableDataInfo<ControlDataDto> list(ControlDataVo vo){
        return service.list(vo);
    }

    @GetMapping("/export")
    @ApiOperation("导出")
    @Log(title = "控制数据->导出", businessType = BusinessType.EXPORT)
    public void export(ControlDataVo vo, HttpServletResponse response){
        service.export(vo, response);
    }

    @GetMapping("/{id}")
    @ApiOperation("详细信息")
    @Log(title = "控制数据->详细信息", businessType = BusinessType.SEARCH)
    public ResultEntity<ControlDataDto> detail(@PathVariable("id") Long id){
        return ResultEntity.success(service.detail(id));
    }

}
