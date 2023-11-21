package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.WarnDataTypeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import com.deyi.daxie.cloud.operation.domain.WarnData;
import com.deyi.daxie.cloud.operation.domain.vo.WarnDataVo;
import com.deyi.daxie.cloud.operation.service.WarnDataService;

@RestController
@RequestMapping("warn-data")
@Api(tags = "报警数据")
public class WarnDataController {

    @Autowired
    private WarnDataService service;

    @GetMapping
    @ApiOperation("分页列表")
    @Log(title = "报警数据->分页列表", businessType = BusinessType.SEARCH)
    public TableDataInfo<WarnDataTypeDto> list(WarnDataVo vo){
        return service.list(vo);
    }

    @GetMapping("/export")
    @ApiOperation("c")
    @Log(title = "报警数据->报警类型列表", businessType = BusinessType.EXPORT)
    public void export(WarnDataVo vo, HttpServletResponse response){
        service.export(vo, response);
    }

    @GetMapping("/type-list")
    @ApiOperation("报警类型列表")
    @Log(title = "报警数据->报警类型列表", businessType = BusinessType.SEARCH)
    public ResultEntity<Map<String, String>> typeList(){
        return ResultEntity.success(service.typeList());
    }

}
