package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.WarnCountDto;
import com.deyi.daxie.cloud.operation.domain.vo.WarnCountVo;
import com.deyi.daxie.cloud.operation.service.WarnDataService;
import com.deyi.daxie.cloud.operation.service.WarnInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class WarnInfoCtroller {
    @Autowired
    private WarnInfoService service;

    @PostMapping("/export")
    @ApiOperation("c")
    @Log(title = "报警数据->报警类型列表", businessType = BusinessType.EXPORT)
    public void exportByTime(@RequestBody WarnCountVo vo, HttpServletResponse response) {
        service.exportByTime(vo, response);
    }

    @PostMapping("/count")
    @ApiOperation("报警类型统计")
    @Log(title = "报警类型统计->报警类型统计列表", businessType = BusinessType.SEARCH)
    public TableDataInfo<List<WarnCountDto>> typeCountList(@RequestBody WarnCountVo vo) {
        Object[] ar = vo.getDeviceTime();
        String start = ar[0].toString();
        String end = ar[1].toString();
        vo.setStartTime(start);
        vo.setEndTime(end);
        List<WarnCountDto> ls = service.typeCountList(vo);
        PageHelper.startPage(vo.getCurrent(),vo.getPageSize());
        PageInfo pageInfo = new PageInfo(ls);
        long total = pageInfo.getTotal();

       /* int count = 0;
        List<WarnCountDto> pageList = null;
        if (ls != null && ls.size() > 0) {
            count = ls.size();
            int fromIndex = vo.getCurrent() * vo.getPageSize();
            int toIndex = (vo.getCurrent() + 1) * vo.getPageSize();
            if (toIndex > count) {
                toIndex = count;
            }
            pageList = ls.subList(fromIndex, toIndex);

        }*/
        return new TableDataInfo<>(pageInfo.getList(),total);
    }
    @GetMapping("/type")
    @ApiOperation("统计周期列表")
    @Log(title = "统计周期->统计周期类型列表", businessType = BusinessType.SEARCH)
    public ResultEntity<Map<String, String>> markType() {
        return ResultEntity.success(service.markType());
    }
}
