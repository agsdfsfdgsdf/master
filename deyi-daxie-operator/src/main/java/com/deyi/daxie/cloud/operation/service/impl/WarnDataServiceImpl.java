package com.deyi.daxie.cloud.operation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.operation.domain.dto.WarnDataDto;
import com.deyi.daxie.cloud.operation.domain.dto.WarnDataTypeDto;
import com.deyi.daxie.cloud.operation.utils.WarnDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.deyi.daxie.cloud.operation.domain.WarnData;
import com.deyi.daxie.cloud.operation.domain.vo.WarnDataVo;
import com.deyi.daxie.cloud.operation.mapper.WarnDataMapper;
import com.deyi.daxie.cloud.operation.service.WarnDataService;

@Slf4j
@Service
public class WarnDataServiceImpl implements WarnDataService {

    @Autowired
    private WarnDataMapper mapper;

    @Override
    public TableDataInfo<WarnDataTypeDto> list(WarnDataVo vo) {
        QueryWrapper<WarnData> wrapper = getQueryWrapper(vo);
        Page<WarnData> page = new Page<>(vo.getCurrent(), vo.getPageSize());
        mapper.selectPage(page, wrapper);
        List<WarnDataTypeDto> ls = new ArrayList<>();
        WarnDataUtil.getTypeList(ls, page.getRecords());
        if(StrUtil.isNotEmpty(vo.getType())){
            ls = ls.stream().filter(it -> StrUtil.equals(WarnDataUtil.getTypeField().get(vo.getType()), it.getType())).collect(Collectors.toList());
        }
        return new TableDataInfo<>(ls, page.getTotal());
    }

    @Override
    public void export(WarnDataVo vo, HttpServletResponse response) {

        QueryWrapper<WarnData> wrapper = getQueryWrapper(vo);
        List<WarnData> data = mapper.selectList(wrapper);
        List<WarnDataTypeDto> list = new ArrayList<>();
        WarnDataUtil.getTypeList(list, data);

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "warn_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), WarnDataTypeDto.class)
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
    public Map<String, String> typeList() {
        return WarnDataUtil.getTypeField();
    }

    @Override
    public List<WarnData> getByRange(Date startTime, Date endTime) {
        return mapper.getByRange(startTime, endTime);
    }

    private QueryWrapper<WarnData> getQueryWrapper(WarnDataVo vo) {
        QueryWrapper<WarnData> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(vo.getType())){
            wrapper.eq(StrUtil.toUnderlineCase(vo.getType()), Boolean.TRUE);
        }
        if(StringUtils.isNotEmpty(vo.getDeviceNum())){
            wrapper.like("device_num", vo.getDeviceNum());
        }
        return wrapper;
    }

}
