package com.deyi.daxie.cloud.websocket.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.*;
import com.deyi.daxie.cloud.operation.domain.dto.WarnDataTypeDto;
import com.deyi.daxie.cloud.operation.service.*;
import com.deyi.daxie.cloud.operation.utils.WarnDataUtil;
import com.deyi.daxie.cloud.vehicle.query.redis.RedisUtil;
import com.deyi.daxie.cloud.vehicle.query.service.VehicleInfoService;
import com.deyi.daxie.cloud.websocket.util.WebsocketClient;
import com.deyi.daxie.cloud.websocket.domain.*;
import com.deyi.daxie.cloud.websocket.service.WebSocketService;
import com.deyi.daxie.cloud.websocket.util.GPSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/6/5
 */
@Slf4j
@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    private VehicleInfoService vehicleInfoService;

    @Autowired
    private StatusDataService statusService;

    @Autowired
    private ControlDataService controlService;

    @Autowired
    private AligningDataService aligningService;

    @Autowired
    private MissionDataService missionDataService;

    @Autowired
    private WarnDataService warnService;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${redis.onlineKey}")
    private String onlineKey;

    private String login = "登录";
    private String logout = "未登录";

    @Value("${des.key}")
    private String desKey;

    @Value("${offline.url}")
    private String offlineUrl;

    public static Map<String, WebsocketClient> sessionMap = new HashMap<>();

    @Override
    @Log(title = "首页数据", businessType = BusinessType.WS)
    public void home(ParamVo paramVo) {
        log.info("首页数据处理 --> {}", paramVo);
        SocketResultDto<HomeDto> result = new SocketResultDto<>();
        result.setMethod(ParamVo.HOME);
        HomeDto dto = new HomeDto();
        result.setData(dto);
        Date endTime = new Date();
        Date startTime = DateUtil.beginOfDay(DateUtil.offsetDay(endTime, -6));
        InfoMessage info = new InfoMessage();
        info.setTime(DateUtil.formatTime(endTime));
        dto.setInfo(info);

        // 车辆数据
        Set<String> deviceNum = vehicleInfoService.getDeviceNum(paramVo.getDeviceNum());

        if(CollectionUtils.isEmpty(deviceNum)) {
            sendMessage(paramVo.getSession(), result);
        }

        AtomicReference<Integer> online = new AtomicReference<>(0);
        AtomicReference<Integer> offline = new AtomicReference<>(0);
        deviceNum.forEach(num -> {
            Object token = redisUtil.hget(num, onlineKey);
            if(token != null && BooleanUtil.toBoolean(token+"")){
                online.getAndSet(online.get() + 1);
            }else{
                offline.getAndSet(offline.get() + 1);
            }
        });
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setVehicleOnline(online.get());
        vehicleInfo.setVehicleOffline(offline.get());
        dto.setVehicle(vehicleInfo);

        // 作业数据获取
        List<MissionData> missions = missionDataService.selectByRange(startTime, endTime);

        // 处理数据，获取箱数
        List<String> containerNos = missions.stream().map(MissionData::getContainerNo).distinct().collect(Collectors.toList());
        info.setContainerCount(containerNos.size());

        // 运行时长
        Long runTime = missions.stream()
                .map(it -> DateUtil.between(DateUtil.parseDateTime(it.getReceivungTime()), DateUtil.parseDateTime(it.getFinishTime()), DateUnit.SECOND))
                .reduce(0L, (acc, n) -> acc + n);
        info.setRunTime(getTime(runTime));

        // 按天分类
        Map<String, List<MissionData>> missionMap = new HashMap<>();
        missions.forEach(it -> {
            String date = it.getFinishTime().substring(0, 10);
            List<MissionData> missionData = missionMap.get(date);
            if(missionData == null){
                missionData = new ArrayList<>();
                missionMap.put(date, missionData);
            }
            missionData.add(it);
        });

        String yesterday = DateUtil.formatDate(DateUtil.beginOfDay(DateUtil.offsetDay(endTime, -1)));
        String today = DateUtil.formatDate(DateUtil.beginOfDay(endTime));
        // 昨日时长
        Long yesterdayTime = CollUtil.emptyIfNull(missionMap.get(yesterday)).stream()
                .map(it -> DateUtil.between(DateUtil.parseDateTime(it.getReceivungTime()), DateUtil.parseDateTime(it.getFinishTime()), DateUnit.SECOND))
                .reduce(0L, (acc, n) -> acc + n);
        // 今日时长
        Long todayTime = CollUtil.emptyIfNull(missionMap.get(today)).stream()
                .map(it -> DateUtil.between(DateUtil.parseDateTime(it.getReceivungTime()), DateUtil.parseDateTime(it.getFinishTime()), DateUnit.SECOND))
                .reduce(0L, (acc, n) -> acc + n);
        info.setCompareTime(getTime(todayTime - yesterdayTime));

        // 计算
        List<WeekWork> weekWorks = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        for (Date start = DateUtil.beginOfDay(startTime); DateUtil.compare(start, endTime) < 0; start = DateUtil.offsetDay(start, 1)){
            dates.add(DateUtil.formatDate(start));
        }

        for (String date: dates){
            List<MissionData> value = missionMap.get(date);
            WeekWork weekWork = new WeekWork();
            weekWork.setDate(date);
            if(value == null){
                weekWorks.add(weekWork);
                continue;
            }
            Long timeCount = value.stream().map(it -> DateUtil.between(DateUtil.parseDateTime(it.getReceivungTime()), DateUtil.parseDateTime(it.getFinishTime()), DateUnit.SECOND))
                    .reduce(0L, (acc, n) -> acc + n);
            weekWork.setTime(timeCount);
            int count = value.stream().map(MissionData::getContainerNo).collect(Collectors.toList()).size();
            weekWork.setContainerCount(count);
            weekWork.setEfficiency(count * 3600.0F / timeCount);
            weekWorks.add(weekWork);
        }
        dto.setWeekWorks(weekWorks);

        // 报警
        List<WarnData> warns = warnService.getByRange(startTime, endTime);
        List<WarnDataTypeDto> warnDataType = new ArrayList<>();
        WarnDataUtil.getTypeList(warnDataType, warns);
        Map<String, Integer> warnCount = new HashMap<>();
        warnDataType.forEach(it -> {
            String type = it.getType();
            warnCount.merge(type, 1, Integer::sum);
        });
        dto.setWarnCount(warnCount);

        // 对位数据
        List<AligningData> alignings = aligningService.getByRange(startTime, endTime);
        ParaPosition paraPosition = new ParaPosition();
        dto.setPosition(paraPosition);
        // 手工对位
        Long manualCount = alignings.stream().filter(it -> !it.getControlMode()).count();
        paraPosition.setManualCount(manualCount);
        // 自动对位
        Long automatic = alignings.stream().filter(AligningData::getControlMode).count();
        paraPosition.setAutomatic(automatic);

        // 平均速度
        List<ControlData> controls = controlService.getByRange(startTime, endTime);
        Double speed = controls.stream().map(ControlData::getSpeedR).reduce(0.0, (acc, n) -> acc + n);
        info.setAvgSpeed(String.format("%.2fkm/h", speed / controls.size()));

        // 总里程
        Map<String, List<ControlData>> groupLs = controls.stream().collect(Collectors.groupingBy(ControlData::getDeviceNum));
        AtomicReference<Double> allMileage = new AtomicReference<>(0.0);
        groupLs.values().stream().filter(it -> it.size() > 1).forEach(it -> {
            allMileage.updateAndGet(v -> v + getMileage(it));
        });
        info.setAllMileage(GPSUtils.format(allMileage.get()));

        // 比较昨日新增里程
        groupLs = controls.stream().collect(Collectors.groupingBy(it -> it.getDeviceTime().substring(0, 10)));
        // 当日里程
        Double todayMileage = 0.0;
        if(groupLs.get(dates.get(dates.size() - 1)) != null){
            todayMileage = getMileage(groupLs.get(dates.get(dates.size() - 1)));
        }
        // 昨日里程
        Double yesterdayMileage = 0.0;
        if(groupLs.get(dates.get(dates.size() - 2)) != null){
            yesterdayMileage = getMileage(groupLs.get(dates.get(dates.size() - 2)));
        }
        info.setCompareMileage(GPSUtils.format(todayMileage - yesterdayMileage));

        AutoDrive autoDrive = new AutoDrive();
        autoDrive.setFaultCount(10);
        autoDrive.setFaultTime(10L);
        autoDrive.setTakeoversCount(10);
        autoDrive.setTakeoversTime(10L);

        sendMessage(paramVo.getSession(), result);
    }


    @Override
    @Log(title = "首页数据", businessType = BusinessType.WS)
    public void driverNum(ParamVo paramVo) {
        SocketResultDto<Collection<LocationDto>> result = new SocketResultDto<>();
        result.setMethod(ParamVo.DRIVER_NUM);
        log.info("位置监控获取车辆列表 --> {}", paramVo);
        Map<String, LocationDto> data = new HashMap<>();
        Set<String> deviceNum = vehicleInfoService.getDeviceNum(paramVo.getDeviceNum());

        if(CollectionUtils.isEmpty(deviceNum)) {
            result.setData(data.values());
            sendMessage(paramVo.getSession(), result);
            return ;
        }
        deviceNum.forEach(num -> {
            LocationDto dto = new LocationDto();
            dto.setDeviceNum(num);
            data.put(num, dto);
        });

        // 获取车号、作业模式
        List<StatusData> status = statusService.getStatusByList(deviceNum);
        status.forEach(it -> {
            LocationDto dto = data.get(it.getDeviceNum());
            dto.setOperationMode(it.getOperationMode());
            data.put(dto.getDeviceNum(), dto);
        });
        result.setData(data.values());
        sendMessage(paramVo.getSession(), result);

    }

    @Override
    @Log(title = "车辆实时监控", businessType = BusinessType.WS)
    public void location(ParamVo paramVo) {
        SocketResultDto<Collection<LocationDto>> result = new SocketResultDto<>();
        result.setMethod(ParamVo.LOCATION);
        log.info("位置监控数据处理 --> {}", paramVo);
        Map<String, LocationDto> data = new HashMap<>();
        Set<String> deviceNum = vehicleInfoService.getDeviceNum(paramVo.getDeviceNum());

        if(CollectionUtils.isEmpty(deviceNum)) {
            result.setData(data.values());
            sendMessage(paramVo.getSession(), result);
            return ;
        }
        deviceNum.forEach(num -> {
            LocationDto dto = new LocationDto();
            dto.setDeviceNum(num);
            data.put(num, dto);
        });

        // 获取车号、作业模式
        List<StatusData> status = statusService.getStatusByList(deviceNum);
        status.forEach(it -> {
            LocationDto dto = data.get(it.getDeviceNum());
            dto.setOperationMode(it.getOperationMode());
            data.put(dto.getDeviceNum(), dto);
        });


        // 读取redis，获取登录状态
        deviceNum.forEach(num -> {
            Object token = redisUtil.hget(num, onlineKey);
            if(token != null && BooleanUtil.toBoolean(token+"")){
                data.get(num).setTos(login);
            }else{
                data.get(num).setTos(logout);
            }
        });

        // 获取作业箱号
        List<MissionData> mission = missionDataService.getByDeviceNums(deviceNum);
        mission.forEach(it -> {
            LocationDto dto = data.get(it.getDeviceNum());
            dto.setContainerNo(it.getContainerNo());
        });

        // 获取经纬度信息
        List<ControlData> location = controlService.getLocation(deviceNum);
        location.forEach(it -> {
            LocationDto dto = data.get(it.getDeviceNum());
            dto.setSpeed(it.getSpeedR());
            dto.setLatitude(it.getLatitudeR());
            dto.setLongitude(it.getLongitudeR());
            dto.setTimestamp(it.getDeviceTime());
        });

        result.setData(data.values());
        sendMessage(paramVo.getSession(), result);

    }

    @Override
    @Log(title = "强制下线", businessType = BusinessType.WS)
    public void offline(ParamVo paramVo) {
        try {
            SocketResultDto result = new SocketResultDto();
            result.setMethod(ParamVo.OFF_LINE);
            com.deyi.daxie.cloud.vehicle.query.vo.VehicleInfo info = vehicleInfoService.selectByDeviceNum(paramVo.getDeviceNum());
            if(info == null){
                result.setData("车辆无密码");
                sendMessage(paramVo.getSession(), result);
                return;
            }
            String pwd = SecureUtil.des(desKey.getBytes()).decryptStr(info.getTscPwd());
            WebsocketClient client = new WebsocketClient(new URI(offlineUrl + "/" + paramVo.getDeviceNum()), paramVo.getSession(), paramVo.getDeviceNum());
            client.connect();
            sessionMap.put(paramVo.getDeviceNum(), client);
            OffLineVo offline = new OffLineVo();
            offline.setTruckNo(paramVo.getDeviceNum());
            offline.setUsername(paramVo.getDeviceNum());
            offline.setPassword(pwd);
            client.send(JSON.toJSONString(offline));
        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }

    private void sendMessage(WebSocketSession session, Object data){
        try {
            String json = JSON.toJSONString(data);
            log.info("send: {}", json);
            session.sendMessage(new TextMessage(json));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 计算总里程
     * @param data
     * @return
     */
    private Double getMileage(List<ControlData> data){
        Double allMileage = 0.0;
        for(int i = 0; i < data.size() - 1; i++){
            int finalI = i;
            allMileage += GPSUtils.getDistance(data.get(finalI).getLongitudeR(), data.get(finalI).getLatitudeR(), data.get(finalI + 1).getLongitudeR(), data.get(finalI + 1).getLatitudeR());
        }
        return allMileage;
    }

    private String getTime(Long time){

        if(Math.abs(time) < 60){
            return String.format("%ss", time);
        } else if(time < 3600){
            return String.format("%smin", time/60);
        } else{
            return String.format("%sh", time/3600);
        }
    }
}
