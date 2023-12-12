package com.deyi.daxie.cloud.operation.service.impl;

import com.deyi.daxie.cloud.operation.domain.OutLane;
import com.deyi.daxie.cloud.operation.mapper.OutLaneMapper;
import com.deyi.daxie.cloud.operation.service.OutLaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutLaneServiceImpl implements OutLaneService {
    @Autowired
    private OutLaneMapper outLaneMapper;
    @Override
    public void save(List<OutLane> ls) {
        outLaneMapper.save(ls);
    }
}
