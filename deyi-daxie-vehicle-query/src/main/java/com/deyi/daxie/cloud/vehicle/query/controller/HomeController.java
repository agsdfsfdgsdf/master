package com.deyi.daxie.cloud.vehicle.query.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.vehicle.query.redis.RedisUtil;
import com.deyi.daxie.cloud.vehicle.query.service.HomeService;
import com.deyi.daxie.cloud.vehicle.query.service.LocationInfoService;
import com.deyi.daxie.cloud.vehicle.query.service.StatisticService;
import com.deyi.daxie.cloud.vehicle.query.service.VehicleInfoService;
import com.deyi.daxie.cloud.vehicle.query.util.DateFormat;
import com.deyi.daxie.cloud.vehicle.query.util.Result;
import com.deyi.daxie.cloud.vehicle.query.vo.Statistic;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Description: 车辆接口控制类
 *
 * @author Huang ShuYing
 * @date 2022/9/25
 */
@RestController
@RequestMapping("/vehicle/home")
@Api(tags = "车辆接口-home")
public class HomeController {

    @Autowired
    private VehicleInfoService vehicleInfoService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private LocationInfoService locationInfoService;

    @GetMapping("/list")
    @ApiOperation("列表")
    @Log(title = "车辆接口:home->列表", businessType = BusinessType.SEARCH)
    public Result list() {
        //运营里程
        JSONArray jsonArray = new JSONArray();
        //运行总时长
        JSONArray jsonArray3 = new JSONArray();
        //今日日期
        String today = DateFormat.getDate(0);
        Statistic statistic = statisticService.total(today);
        //昨日日期
        String yesterday = DateFormat.getDate(1);
        Statistic statistic1 = statisticService.total(yesterday);
        if (statistic1 != null&&statistic!= null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total", String.format("%.2f",statistic.getMileage() / 1000));
            jsonObject.put("discrepancy", String.format("%.2f",(statistic.getMileage() - statistic1.getMileage()) / 1000));
            jsonArray.add(jsonObject);
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("total", String.format("%.2f",statistic.getDuration() / 1000 / 60 / 60));
            jsonObject3.put("discrepancy", String.format("%.2f",(statistic.getDuration() - statistic1.getDuration()) / 1000 / 60 / 60));
            jsonArray3.add(jsonObject3);
        } else if (statistic != null && statistic1 == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total", String.format("%.2f",statistic.getMileage() / 1000));
            jsonObject.put("discrepancy", String.format("%.2f",statistic.getMileage() / 1000));
            jsonArray.add(jsonObject);
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("total", String.format("%.2f",statistic.getDuration() / 1000 / 60 / 60));
            jsonObject3.put("discrepancy", String.format("%.2f",statistic.getDuration() / 1000 / 60 / 60));
            jsonArray3.add(jsonObject3);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total", 0);
            jsonObject.put("discrepancy", 0);
            jsonArray.add(jsonObject);
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("total", 0);
            jsonObject3.put("discrepancy", 0);
            jsonArray3.add(jsonObject3);
        }
        //总运营车辆
        JSONArray jsonArray1 = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("total", vehicleInfoService.vehicleTotal());
        jsonObject1.put("time", DateFormat.timestampToDate(new Date()));
        jsonArray1.add(jsonObject1);
        //次数
        JSONArray jsonArray2 = homeService.taskCount();
        //车辆运营速度分布
        JSONArray jsonArray4 = locationInfoService.list();
        //在线车辆
        JSONArray jsonArray5 = new JSONArray();
        JSONObject jsonObject5 = new JSONObject();
        Map<Object, Object> map = redisUtil.hmget("task_location");
        jsonObject5.put("total", map.size());
        jsonObject5.put("time", DateFormat.timestampToDate(new Date()));
        jsonArray5.add(jsonObject5);
        //警告
        JSONArray jsonArray6 = new JSONArray();
        JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("normal", 90);
        jsonObject6.put("commonly", 8);
        jsonObject6.put("serious", 2);
        jsonArray6.add(jsonObject6);
        //常用
        JSONArray jsonArray7 = homeService.common();
        JSONObject jsonObject0 = new JSONObject();
        jsonObject0.put("mileage", jsonArray);
        jsonObject0.put("VehicleCount", jsonArray1);
        jsonObject0.put("RunCount", jsonArray2);
        jsonObject0.put("time", jsonArray3);
        jsonObject0.put("speed", jsonArray4);
        jsonObject0.put("online", jsonArray5);
        jsonObject0.put("warning", jsonArray6);
        jsonObject0.put("Common", jsonArray7);
        return Result.success(jsonObject0);
    }

}
