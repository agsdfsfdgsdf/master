package com.deyi.daxie.cloud.vehicle.query.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.vehicle.query.service.VehicleInfoService;
import com.deyi.daxie.cloud.vehicle.query.util.Result;
import com.deyi.daxie.cloud.vehicle.query.vo.TableDataInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.VehicleInfo;
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
@RequestMapping("/vehicle")
@Api(tags = "车辆接口-vehicle")
public class VehicleInfoController {

    @Autowired
    private VehicleInfoService vehicleInfoService;

    @GetMapping("/list")
    @ApiOperation("列表")
    @Log(title = "车辆接口:vehicle->列表", businessType = BusinessType.SEARCH)
    public TableDataInfo list(
            @RequestParam(value = "current", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "vin", defaultValue = "") String vin,
            @RequestParam(value = "plateNumber", defaultValue = "") String plateNumber,
            @RequestParam(value = "deviceNum", defaultValue = "") String deviceNum,
            @RequestParam(value = "company", defaultValue = "") String company,
            @RequestParam(value = "protocol", defaultValue = "") String protocol,
            @RequestParam(value = "vehicleType", defaultValue = "") String vehicleType,
            @RequestParam(value = "vehicleStatus", defaultValue = "") String vehicleStatus) {
        return vehicleInfoService.list(pageSize, pageNum, vin,plateNumber,company, protocol, vehicleType, vehicleStatus, deviceNum);
    }

    @GetMapping("/detail/{vin}")
    @ApiOperation("详情")
    @Log(title = "车辆接口:vehicle->详情", businessType = BusinessType.SEARCH)
    public Result info(@PathVariable("vin") String vin) {
        return vehicleInfoService.info(vin);
    }

    @PostMapping("/add")
    @ApiOperation("新增")
    @Log(title = "车辆接口:vehicle->新增", businessType = BusinessType.INSERT)
    public Result add(@RequestBody VehicleInfo vehicleInfo) {
        return vehicleInfoService.add(vehicleInfo);
    }

    @PutMapping("/edit")
    @ApiOperation("编辑")
    @Log(title = "车辆接口:vehicle->编辑", businessType = BusinessType.UPDATE)
    public Result edit(@RequestBody VehicleInfo vehicleInfo) {
        return vehicleInfoService.edit(vehicleInfo);
    }

    @DeleteMapping("/delete/{vin}")
    @ApiOperation("删除")
    @Log(title = "车辆接口:vehicle->删除", businessType = BusinessType.DELETE)
    public Result remove(@PathVariable("vin") String vin) {
        return vehicleInfoService.remove(vin);
    }

}
