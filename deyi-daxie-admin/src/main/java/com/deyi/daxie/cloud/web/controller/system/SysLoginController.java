package com.deyi.daxie.cloud.web.controller.system;

import java.util.List;
import java.util.Set;

import com.deyi.daxie.cloud.common.annotation.Log;
import com.deyi.daxie.cloud.common.constant.Constants;
import com.deyi.daxie.cloud.common.core.domain.AjaxResult;
import com.deyi.daxie.cloud.common.core.domain.entity.SysMenu;
import com.deyi.daxie.cloud.common.core.domain.entity.SysUser;
import com.deyi.daxie.cloud.common.enums.BusinessType;
import com.deyi.daxie.cloud.common.utils.SecurityUtils;
import com.deyi.daxie.cloud.framework.web.service.SysLoginService;
import com.deyi.daxie.cloud.framework.web.service.SysPermissionService;
import com.deyi.daxie.cloud.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.deyi.daxie.cloud.common.core.domain.model.LoginBody;

/**
 * 登录验证
 * 
 * @author Huang ShuYing
 */
@Api("登录验证")
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("登录方法")
    @PostMapping("/login")
    @Log(title = "登录方法", businessType = BusinessType.LOGIN)
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @ApiOperation("获取用户信息")
    @GetMapping("getInfo")
    @Log(title = "获取用户信息", businessType = BusinessType.SEARCH)
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @ApiOperation("获取路由信息")
    @GetMapping("getRouters")
    @Log(title = "获取路由信息", businessType = BusinessType.SEARCH)
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
