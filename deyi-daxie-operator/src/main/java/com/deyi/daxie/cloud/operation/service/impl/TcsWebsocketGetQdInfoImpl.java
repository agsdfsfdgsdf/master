package com.deyi.daxie.cloud.operation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.TcsWebsocketGetQdInfo;
import com.deyi.daxie.cloud.operation.domain.dto.TcsWebsocketGetQdInfoDto;
import com.deyi.daxie.cloud.operation.domain.vo.TcsWebsocketGetQdInfoVo;
import com.deyi.daxie.cloud.operation.mapper.TcsWebsocketGetQdInfoMapper;
import com.deyi.daxie.cloud.operation.service.TcsWebsocketGetQdInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TcsWebsocketGetQdInfoImpl implements TcsWebsocketGetQdInfoService {
    @Resource
    private TcsWebsocketGetQdInfoMapper tcsWebsocketGetQdInfoMapper;
    @Override
    public TableDataInfo<TcsWebsocketGetQdInfoDto> list(TcsWebsocketGetQdInfoVo vo) {
        QueryWrapper<TcsWebsocketGetQdInfo> wrapper = getQueryWrapper(vo);
        Page<TcsWebsocketGetQdInfo> page = new Page<>(vo.getCurrent(),vo.getPageSize());
        tcsWebsocketGetQdInfoMapper.selectPage(page,wrapper);
        List<TcsWebsocketGetQdInfoDto> list = new ArrayList();
        if(StrUtil.isNotEmpty(vo.getTruckNo())||StrUtil.isNotEmpty(vo.getTime())||StrUtil.isNotEmpty(vo.getResName())){
            for (TcsWebsocketGetQdInfo tcsWebsocketGetQdInfo :page.getRecords()){
                TcsWebsocketGetQdInfoDto tcsWebsocketGetQdInfoDto = new TcsWebsocketGetQdInfoDto();
                tcsWebsocketGetQdInfoDto.setId(tcsWebsocketGetQdInfo.getId());
                tcsWebsocketGetQdInfoDto.setTime(tcsWebsocketGetQdInfo.getTime().toString());
                tcsWebsocketGetQdInfoDto.setTruckNo(tcsWebsocketGetQdInfo.getTruckNo());
                tcsWebsocketGetQdInfoDto.setResId(tcsWebsocketGetQdInfo.getResId());
                tcsWebsocketGetQdInfoDto.setResChangedtype(tcsWebsocketGetQdInfo.getResChangedtype());
                tcsWebsocketGetQdInfoDto.setResName(tcsWebsocketGetQdInfo.getResName());
                tcsWebsocketGetQdInfoDto.setResPosition(tcsWebsocketGetQdInfo.getResPosition());
                tcsWebsocketGetQdInfoDto.setResClosedlanes(tcsWebsocketGetQdInfo.getResClosedlanes());
                tcsWebsocketGetQdInfoDto.setResRefreshtime(tcsWebsocketGetQdInfo.getResRefreshtime());
                tcsWebsocketGetQdInfoDto.setResRefreshtimevalue(tcsWebsocketGetQdInfo.getResRefreshtimevalue());
                tcsWebsocketGetQdInfoDto.setResSling(tcsWebsocketGetQdInfo.getResSling());
                tcsWebsocketGetQdInfoDto.setResState(tcsWebsocketGetQdInfo.getResState());
                list.add(tcsWebsocketGetQdInfoDto);
            }
        }
        return new TableDataInfo<>(list, page.getTotal());
    }

    @Override
    public void export(TcsWebsocketGetQdInfoVo vo, HttpServletResponse response) {
        QueryWrapper<TcsWebsocketGetQdInfo> wrapper = getQueryWrapper(vo);
        List<TcsWebsocketGetQdInfo> data = tcsWebsocketGetQdInfoMapper.selectList(wrapper);
        List<TcsWebsocketGetQdInfoDto> list = getTcsWebsocketGetQdInfoDto(data);
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "tcsWebsocketGetQdInfo_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), TcsWebsocketGetQdInfoDto.class)
                    .registerWriteHandler(new FillStyleCellWriteHandler())
                    .sheet("sheet")
                    .doWrite(list);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public QueryWrapper<TcsWebsocketGetQdInfo> getQueryWrapper(TcsWebsocketGetQdInfoVo vo){
        QueryWrapper<TcsWebsocketGetQdInfo> wrapper = new QueryWrapper<>();
        Integer num =vo.getResState();
        System.out.println(num);
        if(StringUtils.isNotEmpty(vo.getTruckNo())){
            wrapper.eq("truck_no", vo.getTruckNo());
        }
        if(StringUtils.isNotEmpty(vo.getResName())){
            wrapper.eq("res_Name", vo.getResName());
        }
        if(StringUtils.isNotEmpty(vo.getTime())){
            wrapper.like("time", vo.getTime());
        }
        return wrapper;
    }
    public List<TcsWebsocketGetQdInfoDto> getTcsWebsocketGetQdInfoDto(List<TcsWebsocketGetQdInfo> ls){
        List<TcsWebsocketGetQdInfoDto> list = new ArrayList();
        if(ls.size()>0&&ls!=null){
            for (TcsWebsocketGetQdInfo tcsWebsocketGetQdInfo : ls){
                TcsWebsocketGetQdInfoDto tcsWebsocketGetQdInfoDto = new TcsWebsocketGetQdInfoDto();
                tcsWebsocketGetQdInfoDto.setId(tcsWebsocketGetQdInfo.getId());
                tcsWebsocketGetQdInfoDto.setTime(tcsWebsocketGetQdInfo.getTime().toString());
                tcsWebsocketGetQdInfoDto.setTruckNo(tcsWebsocketGetQdInfo.getTruckNo());
                tcsWebsocketGetQdInfoDto.setResId(tcsWebsocketGetQdInfo.getResId());
                tcsWebsocketGetQdInfoDto.setResChangedtype(tcsWebsocketGetQdInfo.getResChangedtype());
                tcsWebsocketGetQdInfoDto.setResName(tcsWebsocketGetQdInfo.getResName());
                tcsWebsocketGetQdInfoDto.setResPosition(tcsWebsocketGetQdInfo.getResPosition());
                tcsWebsocketGetQdInfoDto.setResClosedlanes(tcsWebsocketGetQdInfo.getResClosedlanes());
                tcsWebsocketGetQdInfoDto.setResRefreshtime(tcsWebsocketGetQdInfo.getResRefreshtime());
                tcsWebsocketGetQdInfoDto.setResRefreshtimevalue(tcsWebsocketGetQdInfo.getResRefreshtimevalue());
                tcsWebsocketGetQdInfoDto.setResSling(tcsWebsocketGetQdInfo.getResSling());
                tcsWebsocketGetQdInfoDto.setResState(tcsWebsocketGetQdInfo.getResState());
                list.add(tcsWebsocketGetQdInfoDto);
            }
        }
        return list;
    }
}
