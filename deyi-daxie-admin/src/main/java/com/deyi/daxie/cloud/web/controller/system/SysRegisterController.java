package com.deyi.daxie.cloud.web.controller.system;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.core.controller.BaseController;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import com.deyi.daxie.cloud.framework.web.service.SysRegisterService;
import com.deyi.daxie.cloud.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.deyi.daxie.cloud.common.core.domain.AjaxResult;
import com.deyi.daxie.cloud.common.core.domain.model.RegisterBody;

/**
 * 注册验证
 * 
 * @author Huang ShuYing
 */
@Api("注册验证")
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @ApiOperation("注册")
    @PostMapping("/register")
    @Log(title = "注册", businessType = BusinessType.REGISTER)
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

}
