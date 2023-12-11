package com.deyi.daxie.cloud.operation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.TcsGetInstructions;
import com.deyi.daxie.cloud.operation.domain.dto.TcsGetInstructionsDto;
import com.deyi.daxie.cloud.operation.domain.vo.TcsGetInstructionsVo;
import com.deyi.daxie.cloud.operation.mapper.TcsGetInstructionsMapper;
import com.deyi.daxie.cloud.operation.service.InstructionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class InstructionsServiceImpl implements InstructionsService {

    @Autowired
    private TcsGetInstructionsMapper tcsGetInstructionsMapper;

    @Override
    public TableDataInfo<TcsGetInstructionsDto> list(TcsGetInstructionsVo vo) {

        QueryWrapper<TcsGetInstructions> wrapper = getQueryWrapper(vo);
        Page<TcsGetInstructions> page = new Page<>(vo.getCurrent(),vo.getPageSize());
        tcsGetInstructionsMapper.selectPage(page,wrapper);
        List<TcsGetInstructionsDto> list = new ArrayList();
        if(StrUtil.isNotEmpty(vo.getTruckNo())||StrUtil.isNotEmpty(vo.getTime())){
            for (TcsGetInstructions tcsGetInstructions :page.getRecords()){
                TcsGetInstructionsDto tcsGetInstructionsDto = new TcsGetInstructionsDto();
                tcsGetInstructionsDto.setId(tcsGetInstructions.getId());
                tcsGetInstructionsDto.setTime(tcsGetInstructions.getTime());
                tcsGetInstructionsDto.setTruckNo(tcsGetInstructions.getTruckNo());
                tcsGetInstructionsDto.setResData(tcsGetInstructions.getResData());
                list.add(tcsGetInstructionsDto);
            }
        }
        return new TableDataInfo<>(list, page.getTotal());
    }

    @Override
    public void export(TcsGetInstructionsVo vo, HttpServletResponse response) {
        QueryWrapper<TcsGetInstructions> wrapper = getQueryWrapper(vo);
        List<TcsGetInstructions> data = tcsGetInstructionsMapper.selectList(wrapper);
        List<TcsGetInstructionsDto> list = new ArrayList();
        if(StrUtil.isNotEmpty(vo.getTruckNo())||StrUtil.isNotEmpty(vo.getTime())){
            for (TcsGetInstructions tcsGetInstructions :data){
                TcsGetInstructionsDto tcsGetInstructionsDto = new TcsGetInstructionsDto();
                tcsGetInstructionsDto.setId(tcsGetInstructions.getId());
                tcsGetInstructionsDto.setTime(tcsGetInstructions.getTime());
                tcsGetInstructionsDto.setTruckNo(tcsGetInstructions.getTruckNo());
                tcsGetInstructionsDto.setResData(tcsGetInstructions.getResData());
                list.add(tcsGetInstructionsDto);
            }
        }
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "warn_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), TcsGetInstructionsDto.class)
                    .registerWriteHandler(new FillStyleCellWriteHandler())
                    .sheet("sheet")
                    .doWrite(list);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public QueryWrapper<TcsGetInstructions> getQueryWrapper(TcsGetInstructionsVo vo){
        QueryWrapper<TcsGetInstructions> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(vo.getTruckNo())){
            wrapper.eq("truck_no", vo.getTruckNo());
        }
        if(StringUtils.isNotEmpty(vo.getTime())){
            wrapper.like("time", vo.getTime());
        }
        return wrapper;
    }
}
