package com.deyi.daxie.cloud.web.controller.system;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.config.RuoYiConfig;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author Huang ShuYing
 */
@RestController
public class SysIndexController
{
    /** 系统基础配置 */
    @Autowired
    private RuoYiConfig ruoyiConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    @Log(title = "系统基础配置->访问首页，提示语", businessType = BusinessType.SEARCH)
    public String index()
    {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", ruoyiConfig.getName(), ruoyiConfig.getVersion());
    }
}
