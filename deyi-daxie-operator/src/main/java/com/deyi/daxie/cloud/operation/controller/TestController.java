package com.deyi.daxie.cloud.operation.controller;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.domain.ResultEntity;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.operation.domain.vo.TestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Description: 接口测试
 *
 * @author Chen Xu
 * @date 2023/6/1
 */
@RestController
@RequestMapping("test")
@Api(tags = "测试接口")
public class TestController {

    @GetMapping
    @ApiOperation("调试接口")
    @Log(title = "测试接口", businessType = BusinessType.OTHER)
    public ResultEntity<TestVo> get(TestVo vo){
        return ResultEntity.success(vo);
    }

    @PostMapping
    @ApiOperation("调试接口")
    @Log(title = "测试接口", businessType = BusinessType.OTHER)
    public ResultEntity<TestVo> post(@RequestBody TestVo vo){
        return ResultEntity.success(vo);
    }
}
