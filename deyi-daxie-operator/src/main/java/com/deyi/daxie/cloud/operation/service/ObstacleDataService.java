package com.deyi.daxie.cloud.operation.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.deyi.daxie.cloud.operation.domain.ObstacleData;
import com.deyi.daxie.cloud.operation.domain.vo.ObstacleDataVo;

public interface ObstacleDataService {
    Integer add(ObstacleData entity);

    Integer edit(ObstacleData entity);

    Integer delete(List<Long> ids);

    Page list(ObstacleDataVo vo);

    void export(ObstacleDataVo vo, HttpServletResponse response);

    ObstacleData detail(Long id);
}
