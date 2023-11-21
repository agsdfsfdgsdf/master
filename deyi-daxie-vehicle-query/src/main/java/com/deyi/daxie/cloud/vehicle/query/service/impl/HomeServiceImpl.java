package com.deyi.daxie.cloud.vehicle.query.service.impl;

import com.deyi.daxie.cloud.vehicle.query.mapper.HomeMapper;
import com.deyi.daxie.cloud.vehicle.query.service.HomeService;
import com.deyi.daxie.cloud.vehicle.query.util.DateFormat;
import com.deyi.daxie.cloud.vehicle.query.vo.TaskInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Huang ShuYing
 * @date 2022/9/25
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public JSONArray taskCount() {
        //昨日开始时间戳
        String time = DateFormat.getDate(1);
        List<TaskInfo> taskInfoList = homeMapper.taskList(time);
        int yesterday = 0;
        int today = 0;
        for (int i = 0; i < taskInfoList.size(); i++) {
            if (taskInfoList.get(i).getCreateTime().contains(time)) {
                yesterday++;
            } else {
                today++;
            }
        }
        JSONArray jsonArray2 = new JSONArray();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("total", today);
        jsonObject2.put("discrepancy", today - yesterday);
        jsonArray2.add(jsonObject2);
        return jsonArray2;
    }

    @Override
    public JSONArray common() {
        JSONArray jsonArray = new JSONArray();
        String[] name = new String[]{"云控指挥中心", "车辆列表", "站点管理", "站点下发", "用户管理", "角色管理"};
        String[] url = new String[]{"large-screen", "vehiclemanager/list/index", "station/manager/index", "station/allots/index", "system/user/index", "system/role/index"};
        String[] icon = new String[]{"images/icon_video_large_screen.png", "images/icon_video_vehicle_manager.png", "images/icon_video_station_manger.png", "images/icon_video_station_allots.png", "images/icon_video_system_user.png", "images/icon_video_system_role.png"};
        for (int i = 0; i < name.length; i++) {
            JSONObject jsonObject7 = new JSONObject();
            jsonObject7.put("name", name[i]);
            jsonObject7.put("url", url[i]);
            jsonObject7.put("icon", icon[i]);
            jsonArray.add(jsonObject7);
        }
        return jsonArray;
    }

}
