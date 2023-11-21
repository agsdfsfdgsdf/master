package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.AligningDataDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.AligningData;
import com.deyi.daxie.cloud.operation.domain.vo.AligningDataVo;
import com.deyi.daxie.cloud.operation.service.AligningDataService;

@RestController
@RequestMapping("aligning-data")
@Api(tags = "对位数据")
public class AligningDataController {

    @Autowired
    private AligningDataService service;

    @GetMapping
    @ApiOperation("分页列表")
    @Log(title = "对位数据->分页列表", businessType = BusinessType.SEARCH)
    public TableDataInfo<AligningDataDto> list(AligningDataVo vo){
        return service.list(vo);
    }

    @GetMapping("/export")
    @Log(title = "对位数据->导出", businessType = BusinessType.EXPORT)
    @ApiOperation("导出")
    public void export(AligningDataVo vo, HttpServletResponse response){
        service.export(vo, response);
    }

    @GetMapping("/{id}")
    @ApiOperation("详细信息")
    @Log(title = "对位数据->详细信息", businessType = BusinessType.SEARCH)
    public ResultEntity<AligningDataDto> detail(@PathVariable("id") Long id){
        return ResultEntity.success(service.detail(id));
    }

}
