package com.deyi.daxie.cloud.vehicle.query.service;
import com.deyi.daxie.cloud.vehicle.query.vo.LocationInfo;
import com.alibaba.fastjson.JSONArray;
import java.util.List;

/**
 * Description: 车辆位置存储接口
 * @date 2022/9/25
 * @author Huang ShuYing
 */
public interface LocationInfoService {

        /**
         * Description: 车辆位置存储
         * @param locationInfoList 位置集合
         * @return  int 处理结果
         */
        int add( List<LocationInfo> locationInfoList);

        /**
         * Description:  轨迹集合
         * @return JSONArray
         * @date 2022/10/24
         * @author Huang ShuYing
         */
        JSONArray list();
}
