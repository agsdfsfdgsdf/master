package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.ObstacleData;
import com.deyi.daxie.cloud.operation.domain.vo.ObstacleDataVo;
import com.deyi.daxie.cloud.operation.service.ObstacleDataService;

@RestController
@RequestMapping("obstacle-data")
@Api(tags = "感知决策数据")
public class ObstacleDataController {

    @Autowired
    private ObstacleDataService service;

    @PostMapping
    @ApiOperation("新增")
    @Log(title = "感知决策数据->新增", businessType = BusinessType.INSERT)
    public ResponseEntity add(@RequestBody ObstacleData entity){
        return ResponseEntity.ok(service.add(entity));
    }

    @PutMapping
    @ApiOperation("修改")
    @Log(title = "感知决策数据->修改", businessType = BusinessType.UPDATE)
    public ResponseEntity edit(@RequestBody ObstacleData entity){
        return ResponseEntity.ok(service.edit(entity));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(("删除"))
    @Log(title = "感知决策数据->删除", businessType = BusinessType.DELETE)
    public ResponseEntity delete(@PathVariable("ids") List<Long> ids){
        return ResponseEntity.ok(service.delete(ids));
    }

    @GetMapping
    @ApiOperation("分页列表")
    @Log(title = "感知决策数据->分页列表", businessType = BusinessType.SEARCH)
    public ResponseEntity list(ObstacleDataVo vo){
        return ResponseEntity.ok(service.list(vo));
    }

    @GetMapping("/export")
    @ApiOperation("导出")
    @Log(title = "感知决策数据->导出", businessType = BusinessType.EXPORT)
    public void export(ObstacleDataVo vo, HttpServletResponse response){
        service.export(vo, response);
    }

    @GetMapping("/{id}")
    @ApiOperation("详细信息")
    @Log(title = "感知决策数据->详细信息", businessType = BusinessType.SEARCH)
    public ResponseEntity detail(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.detail(id));
    }

}
