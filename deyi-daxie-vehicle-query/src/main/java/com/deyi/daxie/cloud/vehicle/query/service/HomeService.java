package com.deyi.daxie.cloud.vehicle.query.service;

import com.alibaba.fastjson.JSONArray;

/**
 * Description: 首页
 * @date 2022/9/25
 * @author Huang ShuYing
 */
public interface HomeService {
    /**
     * Description: 任务总数
     * @return 数组
     * @date 2022/10/24
     * @author Huang ShuYing
     */
    JSONArray taskCount();
    /**
     * Description: 常用入口
     * @return 数组
     * @date 2022/10/24
     * @author Huang ShuYing
     */
    JSONArray common();
}
