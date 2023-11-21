package com.deyi.daxie.cloud.framework.security.handle;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson2.JSON;
import com.deyi.daxie.cloud.common.constant.HttpStatus;
import com.deyi.daxie.cloud.common.core.domain.AjaxResult;
import com.deyi.daxie.cloud.common.utils.ServletUtils;
import com.deyi.daxie.cloud.common.utils.StringUtils;

/**
 * 认证失败处理类 返回未授权
 *
 * @author Huang ShuYing
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        response.setHeader("Access-Control-Allow-Origin", "*");
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}
