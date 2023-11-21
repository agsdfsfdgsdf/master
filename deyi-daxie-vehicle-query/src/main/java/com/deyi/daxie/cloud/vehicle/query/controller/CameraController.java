package com.deyi.daxie.cloud.vehicle.query.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.vehicle.query.service.CameraService;
import com.deyi.daxie.cloud.vehicle.query.util.Result;
import com.deyi.daxie.cloud.vehicle.query.vo.CameraInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 车辆接口控制类
 *
 * @author Huang ShuYing
 * @date 2022/9/25
 */
@RestController
@RequestMapping("/vehicle/camera")
@Api(tags = "车辆接口-camera")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @GetMapping("/list")
    @ApiOperation("列表")
    @Log(title = "车辆接口:camera->列表", businessType = BusinessType.SEARCH)
    public TableDataInfo list(
            @RequestParam(value = "current", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "vin", defaultValue = "") String vin,
            @RequestParam(value = "cameraPosition", defaultValue = "") String cameraPosition,
            @RequestParam(value = "cameraId", defaultValue = "") String cameraId) {
        return cameraService.list(pageSize, pageNum, cameraId,vin, cameraPosition);
    }

    @GetMapping("/detail/{cameraId}")
    @ApiOperation("详情")
    @Log(title = "车辆接口:camera->详情", businessType = BusinessType.SEARCH)
    public Result info(@PathVariable("cameraId") String cameraId) {
        return cameraService.detail(cameraId);
    }

    @PostMapping("/add")
    @ApiOperation("新增")
    @Log(title = "车辆接口:camera->新增", businessType = BusinessType.INSERT)
    public Result add(@RequestBody CameraInfo cameraInfo) {
        return cameraService.add(cameraInfo);
    }

    @PutMapping("/edit")
    @ApiOperation("编辑")
    @Log(title = "车辆接口:camera->编辑", businessType = BusinessType.UPDATE)
    public Result edit(@RequestBody CameraInfo cameraInfo) {
        return cameraService.edit(cameraInfo);
    }

    @DeleteMapping("/delete/{cameraId}")
    @ApiOperation("删除")
    @Log(title = "车辆接口:camera->删除", businessType = BusinessType.DELETE)
    public Result remove(@PathVariable("cameraId") String cameraId) {
        return cameraService.remove(cameraId);
    }


}
