package com.deyi.daxie.cloud.operation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.dto.StatusDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import com.deyi.daxie.cloud.operation.domain.StatusData;
import com.deyi.daxie.cloud.operation.domain.vo.StatusDataVo;
import com.deyi.daxie.cloud.operation.mapper.StatusDataMapper;
import com.deyi.daxie.cloud.operation.service.StatusDataService;

@Slf4j
@Service
public class StatusDataServiceImpl implements StatusDataService {

    @Autowired
    private StatusDataMapper mapper;

    @Override
    public TableDataInfo<StatusDataDto> list(StatusDataVo vo) {
        QueryWrapper<StatusData> wrapper = getQueryWrapper(vo);
        Page<StatusData> page = new Page<>(vo.getCurrent(), vo.getPageSize());
        mapper.selectPage(page, wrapper);
        return new TableDataInfo<>(StatusData.toDto(page.getRecords()), page.getTotal());
    }

    @Override
    public void export(StatusDataVo vo, HttpServletResponse response) {

        QueryWrapper<StatusData> wrapper = getQueryWrapper(vo);
        List<StatusData> lst = mapper.selectList(wrapper);
        List<StatusDataDto> list = StatusData.toDto(lst);

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "status_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), StatusDataDto.class)
                .registerWriteHandler(new FillStyleCellWriteHandler())
                .sheet("sheet")
                .doWrite(list);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public StatusDataDto detail(Long id) {
        if(id == null){
            return null;
        }
        StatusData entity = mapper.selectById(id);
        return StatusData.toDto(entity);
    }

    @Override
    public List<StatusData> getStatusByList(Set<String> deviceNum) {
        return mapper.getStatusByList(deviceNum);
    }

    private QueryWrapper<StatusData> getQueryWrapper(StatusDataVo vo) {
        QueryWrapper<StatusData> wrapper = new QueryWrapper<>();
        if(vo.getOperationMode() != null){
            wrapper.eq("operation_mode", vo.getOperationMode());
        }
        if(StringUtils.isNotEmpty(vo.getDeviceNum())){
            wrapper.like("device_num", vo.getDeviceNum());
        }
        if(StringUtils.isNotEmpty(vo.getEnergy()) && !StrUtil.equals("0", vo.getEnergy())){
            wrapper.like("energy", vo.getEnergy());
        }
        return wrapper;
    }

}
