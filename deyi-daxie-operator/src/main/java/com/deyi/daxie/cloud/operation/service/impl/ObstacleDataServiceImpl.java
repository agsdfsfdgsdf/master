package com.deyi.daxie.cloud.operation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.handler.impl.FillStyleCellWriteHandler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deyi.daxie.cloud.operation.domain.dto.ObstacleDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.ObstacleData;
import com.deyi.daxie.cloud.operation.domain.vo.ObstacleDataVo;
import com.deyi.daxie.cloud.operation.mapper.ObstacleDataMapper;
import com.deyi.daxie.cloud.operation.service.ObstacleDataService;

@Slf4j
@Service
public class ObstacleDataServiceImpl implements ObstacleDataService {

    @Autowired
    private ObstacleDataMapper mapper;

    @Override
    public Integer add(ObstacleData entity) {
        System.out.println(entity);
        return mapper.insert(entity);
    }

    @Override
    public Integer edit(ObstacleData entity) {
        if (entity == null || entity.getId() == null) {
            return 0;
        }
        return mapper.updateById(entity);
    }

    @Override
    public Integer delete(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            return 0;
        }
        return mapper.deleteBatchIds(ids);
    }

    @Override
    public Page list(ObstacleDataVo vo) {
        QueryWrapper wrapper = getQueryWrapper(vo);
        Page page = new Page(vo.getCurrent(), vo.getPageSize());
        mapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public void export(ObstacleDataVo vo, HttpServletResponse response) {

        QueryWrapper wrapper = getQueryWrapper(vo);
        wrapper.setEntityClass(ObstacleDataDto.class);
        List<ObstacleDataDto> list = mapper.selectList(wrapper);

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "obstacle_data";
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), ObstacleDataDto.class)
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
    public ObstacleData detail(Long id) {
        if(id == null){
            return null;
        }
        return mapper.selectById(id);
    }


    private QueryWrapper getQueryWrapper(ObstacleDataVo vo) {
        QueryWrapper wrapper = new QueryWrapper();
        return wrapper;
    }

}
