package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.StatusDataDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.StatusData;
import com.deyi.daxie.cloud.operation.domain.vo.StatusDataVo;
import com.deyi.daxie.cloud.operation.service.StatusDataService;

@RestController
@RequestMapping("status-data")
@Api(tags = "车辆状态")
public class StatusDataController {

    @Autowired
    private StatusDataService service;

    @GetMapping
    @ApiOperation("分页列表")
    @Log(title = "车辆状态->分页列表", businessType = BusinessType.SEARCH)
    public TableDataInfo<StatusDataDto> list(StatusDataVo vo){
        return service.list(vo);
    }

    @GetMapping("/export")
    @ApiOperation("导出")
    @Log(title = "车辆状态->导出", businessType = BusinessType.EXPORT)
    public void export(StatusDataVo vo, HttpServletResponse response){
        service.export(vo, response);
    }

    @GetMapping("/{id}")
    @ApiOperation("详细信息")
    @Log(title = "车辆状态->详细信息", businessType = BusinessType.SEARCH)
    public ResultEntity<StatusDataDto> detail(@PathVariable("id") Long id){
        return ResultEntity.success(service.detail(id));
    }

}
