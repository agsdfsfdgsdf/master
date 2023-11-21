package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.AjaxList;
import com.deyi.daxie.cloud.operation.domain.dto.WarnCountDto;
import com.deyi.daxie.cloud.operation.domain.dto.WarnDataTypeDto;
import com.deyi.daxie.cloud.operation.domain.vo.WarnCountVo;
import com.deyi.daxie.cloud.operation.utils.SpiltDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.deyi.daxie.cloud.operation.domain.vo.WarnDataVo;
import com.deyi.daxie.cloud.operation.service.WarnDataService;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/export-count")
    @ApiOperation("c")
    @Log(title = "报警数据->报警类型列表", businessType = BusinessType.EXPORT)
    public void exportByTime(@RequestBody WarnCountVo vo, HttpServletResponse response){
        service.exportByTime(vo, response);
    }

    @PostMapping("/count")
    @ApiOperation("报警类型统计")
    @Log(title = "报警类型统计->报警类型统计列表", businessType = BusinessType.SEARCH)
    public ResponseEntity<Map<SpiltDateUtil.Range,WarnCountDto>> typeCountList(@RequestBody WarnCountVo vo){
        return ResponseEntity.ok(service.typeCountList(vo));
    }
    @GetMapping("/type")
    @ApiOperation("统计周期列表")
    @Log(title = "统计周期->统计周期类型列表", businessType = BusinessType.SEARCH)
    public ResultEntity<Map<String, String>> markType() {
        return ResultEntity.success(service.markType());
    }
    @PostMapping("/upload")
    public String parseAndAdd(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "deviceNum")String deviceNum) throws IOException {
        AjaxList<String> ajaxList = service.handlerUpload(file, deviceNum);
        return ajaxList.getData();
    }
}
