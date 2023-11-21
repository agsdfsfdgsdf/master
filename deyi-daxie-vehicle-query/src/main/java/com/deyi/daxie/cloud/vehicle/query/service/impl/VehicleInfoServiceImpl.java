package com.deyi.daxie.cloud.vehicle.query.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.deyi.daxie.cloud.vehicle.query.mapper.VehicleInfoMapper;
import com.deyi.daxie.cloud.vehicle.query.service.VehicleInfoService;
import com.deyi.daxie.cloud.vehicle.query.util.HttpStatus;
import com.deyi.daxie.cloud.vehicle.query.util.Result;
import com.deyi.daxie.cloud.vehicle.query.util.StringUtils;
import com.deyi.daxie.cloud.vehicle.query.vo.TableDataInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.VehicleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Huang ShuYing
 * @date 2022/9/25
 */
@Service
public class VehicleInfoServiceImpl implements VehicleInfoService {

    @Autowired
    private VehicleInfoMapper vehicleInfoMapper;

    @Value("${des.key}")
    private String desKey;

    @Override
    public TableDataInfo list(int pageSize, int pageNum, String vin, String plateNumber, String company, String protocol, String vehicleType, String vehicleStatus, String deviceNum) {
        int total = vehicleInfoMapper.total(vin,plateNumber,company, protocol, vehicleType, vehicleStatus, deviceNum);
        List<VehicleInfo> vehicleInfoList = vehicleInfoMapper.list(pageSize * (pageNum-1), pageSize ,vin,plateNumber, company, protocol, vehicleType, vehicleStatus, deviceNum);
        return new TableDataInfo(HttpStatus.SUCCESS, "ok", total, vehicleInfoList);
    }

    @Override
    public Result info(String vin) {
        VehicleInfo vehicleInfo = vehicleInfoMapper.info(vin);
        return Result.success(vehicleInfo);
    }

    @Override
    public Result add(VehicleInfo vehicleInfo) {
        if(StringUtils.isEmpty(vehicleInfo.getTscPwd())){
            return Result.error("密码不存在");
        }
        VehicleInfo vehicle = vehicleInfoMapper.info(vehicleInfo.getVin());
        if (vehicle!=null){
            return Result.error("vin已存在");
        }
        vehicleInfo.setTscPwd(SecureUtil.des(desKey.getBytes()).encryptBase64(vehicleInfo.getTscPwd()));
        return  vehicleInfoMapper.add(vehicleInfo)>0?Result.success():Result.error();
    }

    @Override
    public Result edit(VehicleInfo vehicleInfo) {
        return vehicleInfoMapper.edit(vehicleInfo)>0?Result.success():Result.error();
    }

    @Override
    public Result remove(String vin) {

        return vehicleInfoMapper.remove(vin.split(","))>0?Result.success():Result.error();
    }

    @Override
    public int vehicleTotal() {
        return vehicleInfoMapper.total("","","", "", "", "", "");
    }

    @Override
    public Set<String> getDeviceNum(String deviceNum) {
        Set<String> set = vehicleInfoMapper.getDeviceNum(deviceNum);
        return set.stream().filter(it -> it != null).collect(Collectors.toSet());
    }

    @Override
    public VehicleInfo selectByDeviceNum(String deviceNum) {
        return vehicleInfoMapper.selectByDeviceNum(deviceNum);
    }
}


