package com.deyi.daxie.cloud.vehicle.query.service.impl;


import com.deyi.daxie.cloud.vehicle.query.mapper.StatisticMapper;
import com.deyi.daxie.cloud.vehicle.query.service.StatisticService;
import com.deyi.daxie.cloud.vehicle.query.vo.Statistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Description: 车辆位置存储实现类
 *
 * @author Huang ShuYing
 * @date 2022/9/25
 */
@Slf4j
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticMapper statisticMapper;

    @Override
    public void save(String vin, double distance, long duration) {
        int count=statisticMapper.queryCount(vin);
        //判断是否更新还是添加
        if (count > 0) {
            statisticMapper.update(vin, distance, duration);
        } else {
            statisticMapper.add(vin, distance, duration);
        }
    }

    @Override
    public Statistic total(String  date) {
        return statisticMapper.total(date);
    }

}
