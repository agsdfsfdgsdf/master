package com.deyi.daxie.cloud.vehicle.query.service.impl;

import com.deyi.daxie.cloud.vehicle.query.mapper.CameraAccessMapper;
import com.deyi.daxie.cloud.vehicle.query.service.CameraAccessService;
import com.deyi.daxie.cloud.vehicle.query.vo.AccessInfo;
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
public class CameraAccessServiceImpl implements CameraAccessService {

    @Autowired
    private CameraAccessMapper cameraAccessMapper;

    @Override
    public  List<AccessInfo> list() {
        return cameraAccessMapper.list();
    }


}
