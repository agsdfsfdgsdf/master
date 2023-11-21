package com.deyi.daxie.cloud.websocket.interceptor;

import cn.hutool.core.util.StrUtil;
import com.deyi.daxie.cloud.common.constant.Constants;
import com.deyi.daxie.cloud.common.core.domain.model.LoginUser;
import com.deyi.daxie.cloud.common.exception.GlobalException;
import com.deyi.daxie.cloud.framework.web.service.TokenService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/3/29
 */
@Component
@Slf4j
public class MyWebSocketInterceptor implements HandshakeInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("beforeHandshake");
        List<String> sec = request.getHeaders().get("sec-websocket-protocol");
        if(sec == null || StrUtil.isEmpty(sec.get(0))){
            throw new GlobalException("未登录");
        }
        String token = sec.get(0);
        LoginUser user = tokenService.getUser(token);
        attributes.put("loginName", user.getUsername());
        attributes.put("loginId", user.getUserId());
        response.getHeaders().add("sec-websocket-protocol", token);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("afterHandshake");

    }
}
