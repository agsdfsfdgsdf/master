package com.deyi.daxie.cloud.vehicle.query.service.impl;


import com.deyi.daxie.cloud.vehicle.query.mapper.LocationInfoMapper;
import com.deyi.daxie.cloud.vehicle.query.service.LocationInfoService;
import com.deyi.daxie.cloud.vehicle.query.util.DateFormat;
import com.deyi.daxie.cloud.vehicle.query.vo.LocationInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.SpeedStatistic;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 车辆位置存储实现类
 *
 * @author Huang ShuYing
 * @date 2022/9/25
 */
@Slf4j
@Service
public class LocationInfoServiceImpl implements LocationInfoService {

    @Autowired
    private LocationInfoMapper locationInfoMapper;

    @Override
    public int add(List<LocationInfo> locationInfoList) {
        return locationInfoMapper.add(locationInfoList);
    }


    @Override
    public JSONArray list() {
        //获取上个小时开始时间戳
        long t1 = System.currentTimeMillis() - 1000 * 60 * 60;
        //获取昨天前一个小时时间戳
        long t2 = System.currentTimeMillis() - 1000 * 60 * 60 * 25;
        //获取昨天当前时间戳
        long t3 = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
        List<SpeedStatistic> speedStatistics = locationInfoMapper.list(t1, t2, t3);
        JSONArray jsonArray0 = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        for (int i = 0; i < speedStatistics.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("max", speedStatistics.get(i).getMax());
            jsonObject.put("avg", speedStatistics.get(i).getAvg());
            jsonObject.put("time", DateFormat.timeToDate(speedStatistics.get(i).getTime()));
            //判断时间属于今日还是昨日，将数据添加到昨日/今日对象
            if (speedStatistics.get(i).getTime() > t1) {
                jsonArray0.add(jsonObject);
            } else {
                jsonArray1.add(jsonObject);
            }
        }
        JSONObject js = new JSONObject();
        js.put("today", jsonArray0);
        js.put("yesterday", jsonArray1);
        JSONArray jsonArray4 = new JSONArray();
        jsonArray4.add(js);
        return jsonArray4;
    }
}
