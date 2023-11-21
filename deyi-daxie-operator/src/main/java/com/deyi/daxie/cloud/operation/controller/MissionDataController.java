package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.MissionDataDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.MissionData;
import com.deyi.daxie.cloud.operation.domain.vo.MissionDataVo;
import com.deyi.daxie.cloud.operation.service.MissionDataService;

@RestController
@RequestMapping("mission-data")
@Api(tags = "作业数据")
public class MissionDataController {

    @Autowired
    private MissionDataService service;

    @GetMapping
    @ApiOperation("分页列表")
    @Log(title = "作业数据->分页列表", businessType = BusinessType.SEARCH)
    public TableDataInfo<MissionDataDto> list(MissionDataVo vo){
        return service.list(vo);
    }

    @GetMapping("/export")
    @ApiOperation("导出")
    @Log(title = "作业数据->导出", businessType = BusinessType.EXPORT)
    public void export(MissionDataVo vo, HttpServletResponse response){
        service.export(vo, response);
    }

    @GetMapping("/{id}")
    @ApiOperation("详细信息")
    @Log(title = "作业数据->详细信息", businessType = BusinessType.SEARCH)
    public ResultEntity<MissionDataDto> detail(@PathVariable("id") Long id){
        return ResultEntity.success(service.detail(id));
    }

}
