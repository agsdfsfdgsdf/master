package com.deyi.daxie.cloud.operation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.dto.MissionDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.deyi.daxie.cloud.operation.domain.MissionData;
import com.deyi.daxie.cloud.operation.domain.vo.MissionDataVo;
import com.deyi.daxie.cloud.operation.mapper.MissionDataMapper;
import com.deyi.daxie.cloud.operation.service.MissionDataService;

@Slf4j
@Service
public class MissionDataServiceImpl implements MissionDataService {

    @Autowired
    private MissionDataMapper mapper;


    @Override
    public TableDataInfo<MissionDataDto> list(MissionDataVo vo) {
        QueryWrapper wrapper = getQueryWrapper(vo);
        Page page = new Page(vo.getCurrent(), vo.getPageSize());
        mapper.selectPage(page, wrapper);
        return new TableDataInfo<>(MissionData.toDto(page.getRecords()), page.getTotal());
    }

    @Override
    public void export(MissionDataVo vo, HttpServletResponse response) {

        QueryWrapper wrapper = getQueryWrapper(vo);
        List<MissionData> lst = mapper.selectList(wrapper);
        List<MissionDataDto> list = MissionData.toDto(lst);


        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "mission_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), MissionDataDto.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("sheet")
                .doWrite(list);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public MissionDataDto detail(Long id) {
        if(id == null){
            return null;
        }
        MissionData entity = mapper.selectById(id);
        MissionDataDto dto = MissionData.toDto(entity);
        return dto;
    }

    @Override
    public List<MissionData> getByDeviceNums(Set<String> deviceNum) {
        return mapper.getByDeviceNums(deviceNum);
    }

    @Override
    public List<MissionData> selectByRange(Date startTime, Date endTime) {
        return mapper.selectByRange(startTime, endTime);
    }

    private QueryWrapper<MissionData> getQueryWrapper(MissionDataVo vo) {
        QueryWrapper<MissionData> wrapper = new QueryWrapper<>();
        if(vo.getId() != null){
            wrapper.eq("id", vo.getId());
        }
        if(StringUtils.isNotEmpty(vo.getContainerNo())){
            wrapper.like("container_no", vo.getContainerNo());
        }
        if(StringUtils.isNotEmpty(vo.getDeviceNum())){
            wrapper.like("device_num", vo.getDeviceNum());
        }
        return wrapper;
    }

}
