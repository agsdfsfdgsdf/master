package com.deyi.daxie.cloud.vehicle.query.service.impl;

import com.deyi.daxie.cloud.vehicle.query.mapper.CameraAccessMapper;
import com.deyi.daxie.cloud.vehicle.query.mapper.CameraMapper;
import com.deyi.daxie.cloud.vehicle.query.service.CameraService;
import com.deyi.daxie.cloud.vehicle.query.util.HttpStatus;
import com.deyi.daxie.cloud.vehicle.query.util.IsJsonObject;
import com.deyi.daxie.cloud.vehicle.query.util.Result;
import com.deyi.daxie.cloud.vehicle.query.util.http.HttpConnection;
import com.deyi.daxie.cloud.vehicle.query.vo.AccessInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.CameraInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.TableDataInfo;
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
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraMapper cameraMapper;
    @Autowired
    private CameraAccessMapper cameraAccessMapper;

    @Override
    public TableDataInfo list(int pageSize, int pageNum, String cameraId, String vin, String cameraPosition) {
        int total = cameraMapper.total(cameraId, vin, cameraPosition);
        List<CameraInfo> cameraInfoList = cameraMapper.list(pageSize * (pageNum - 1), pageSize, cameraId, vin, cameraPosition);
        return new TableDataInfo(HttpStatus.SUCCESS, "ok", total, cameraInfoList);
    }

    @Override
    public Result detail(String cameraId) {
        return Result.success(cameraMapper.detail(cameraId));
    }

    @Override
    public Result add(CameraInfo cameraInfo) {
        return cameraMapper.add(cameraInfo) > 0 ? Result.success() : Result.error();
    }

    @Override
    public Result edit(CameraInfo cameraInfo) {
        return cameraMapper.edit(cameraInfo) > 0 ? Result.success() : Result.error();
    }

    @Override
    public Result remove(String cameraId) {
        return cameraMapper.remove(cameraId) > 0 ? Result.success() : Result.error();
    }


    @Override
    public void updateAccess() {
        List<AccessInfo> accessInfoList = cameraAccessMapper.list();
        for (int i = 0; i < accessInfoList.size(); i++) {
            String result = HttpConnection.sendPost(accessInfoList.get(i).getUrl(), accessInfoList.get(i).getHeader(),
                    "appKey=" + accessInfoList.get(i).getAppKey() + "&appSecret=" + accessInfoList.get(i).getAppSecret());
            if (IsJsonObject.isJsonObject(result)){
                JSONObject jsonObject=JSONObject.parseObject(result);
                if (jsonObject.getInteger("code")==HttpStatus.SUCCESS){
                    CameraInfo cameraInfo=new CameraInfo();
                    cameraInfo.setAccessToken(jsonObject.getJSONObject("data").getString("accessToken"));
                    cameraInfo.setAccessUpdateId(accessInfoList.get(i).getAccessUpdateId());
                    cameraMapper.updateAccess(cameraInfo);
                }
            }
        }
    }


}
