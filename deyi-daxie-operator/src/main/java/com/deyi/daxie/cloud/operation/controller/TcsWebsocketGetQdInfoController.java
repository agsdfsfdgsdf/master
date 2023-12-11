package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.page.TableDataInfo;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.dto.TcsWebsocketGetQdInfoDto;
import com.deyi.daxie.cloud.operation.domain.vo.TcsWebsocketGetQdInfoVo;
import com.deyi.daxie.cloud.operation.service.TcsWebsocketGetQdInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Api("指令信息")
@RestController
@RequestMapping("/websocketGetQdInfo")
public class TcsWebsocketGetQdInfoController {
    @Resource
    private TcsWebsocketGetQdInfoService tcsWebsocketGetQdInfoService;

    @PostMapping("list")
    @ApiOperation("分页列表")
    @Log(title = "吊桥数据->分页列表", businessType = BusinessType.SEARCH)
    public TableDataInfo<TcsWebsocketGetQdInfoDto> list(@RequestBody TcsWebsocketGetQdInfoVo vo){
        return tcsWebsocketGetQdInfoService.list(vo);
    }
    @GetMapping("/export")
    @ApiOperation("c")
    @Log(title = "吊桥数据->吊桥列表", businessType = BusinessType.EXPORT)
    public void export(TcsWebsocketGetQdInfoVo vo, HttpServletResponse response){
        tcsWebsocketGetQdInfoService.export(vo, response);
    }
}
