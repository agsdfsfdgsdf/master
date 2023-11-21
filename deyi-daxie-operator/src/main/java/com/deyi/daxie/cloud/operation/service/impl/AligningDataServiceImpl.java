package com.deyi.daxie.cloud.operation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.dto.AligningDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.AligningData;
import com.deyi.daxie.cloud.operation.domain.vo.AligningDataVo;
import com.deyi.daxie.cloud.operation.mapper.AligningDataMapper;
import com.deyi.daxie.cloud.operation.service.AligningDataService;

@Slf4j
@Service
public class AligningDataServiceImpl implements AligningDataService {

    @Autowired
    private AligningDataMapper mapper;

    @Override
    public TableDataInfo<AligningDataDto> list(AligningDataVo vo) {
        QueryWrapper<AligningData> wrapper = getQueryWrapper(vo);
        Page<AligningData> page = new Page<>(vo.getCurrent(), vo.getPageSize());
        mapper.selectPage(page, wrapper);
        return new TableDataInfo<>(AligningData.toDto(page.getRecords()), page.getTotal());
    }

    @Override
    public void export(AligningDataVo vo, HttpServletResponse response) {

        QueryWrapper<AligningData> wrapper = getQueryWrapper(vo);
        List<AligningData> lst = mapper.selectList(wrapper);
        List<AligningDataDto> list = AligningData.toDto(lst);

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "aligning_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), AligningDataDto.class)
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
    public AligningDataDto detail(Long id) {
        if(id == null){
            return null;
        }
        AligningData entity = mapper.selectById(id);
        return AligningData.toDto(entity);
    }

    @Override
    public List<AligningData> getByRange(Date startTime, Date endTime) {
        return mapper.getByRange(startTime, endTime);
    }

    private QueryWrapper<AligningData> getQueryWrapper(AligningDataVo vo) {
        QueryWrapper<AligningData> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(vo.getTruckNo())){
            wrapper.like("truckNo", vo.getTruckNo());
        }
        if(StringUtils.isNotEmpty(vo.getContainerDev())){
            wrapper.eq("container_dev", vo.getContainerDev());
        }
        if(StrUtil.isNotEmpty(vo.getDeviceNo())){
            wrapper.eq("deviceNo", vo.getDeviceNo());
        }
        return wrapper;
    }

}
