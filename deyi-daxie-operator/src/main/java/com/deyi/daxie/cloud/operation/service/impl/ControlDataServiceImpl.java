package com.deyi.daxie.cloud.operation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.dto.ControlDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.deyi.daxie.cloud.operation.domain.ControlData;
import com.deyi.daxie.cloud.operation.domain.vo.ControlDataVo;
import com.deyi.daxie.cloud.operation.mapper.ControlDataMapper;
import com.deyi.daxie.cloud.operation.service.ControlDataService;

@Slf4j
@Service
public class ControlDataServiceImpl implements ControlDataService {

    @Autowired
    private ControlDataMapper mapper;

    @Override
    public TableDataInfo<ControlDataDto> list(ControlDataVo vo) {
        QueryWrapper<ControlData> wrapper = getQueryWrapper(vo);
        Page page = new Page(vo.getCurrent(), vo.getPageSize());
        mapper.selectPage(page, wrapper);
        return new TableDataInfo<ControlDataDto>(ControlData.toDto(page.getRecords()), page.getTotal());
    }

    @Override
    public void export(ControlDataVo vo, HttpServletResponse response) {

        QueryWrapper wrapper = getQueryWrapper(vo);
        List<ControlData> lst = mapper.selectList(wrapper);
        List<ControlDataDto> list = ControlData.toDto(lst);

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "control_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), ControlDataDto.class)
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
    public ControlDataDto detail(Long id) {
        if(id == null){
            return null;
        }
        ControlData entity = mapper.selectById(id);
        return ControlData.toDto(entity);
    }

    @Override
    public List<ControlData> getLocation(Set<String> deviceNum) {
        return mapper.getLocation(deviceNum);
    }

    @Override
    public List<ControlData> getByRange(Date startTime, Date endTime) {
        return mapper.getByRange(startTime, endTime);
    }

    private QueryWrapper<ControlData> getQueryWrapper(ControlDataVo vo) {
        QueryWrapper<ControlData> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(vo.getDeviceNum())){
            wrapper.like("device_num", vo.getDeviceNum());
        }
        return wrapper;
    }

}
