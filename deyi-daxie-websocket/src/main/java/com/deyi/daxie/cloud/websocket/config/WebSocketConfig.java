package com.deyi.daxie.cloud.websocket.config;

import com.deyi.daxie.cloud.websocket.handler.MyWebSocketHandler;
import com.deyi.daxie.cloud.websocket.interceptor.MyWebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/3/29
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/ws")
                .setAllowedOriginPatterns("*")
                .addInterceptors(interceptor);
       // registry.addHandler(myWebSocketHandler(), "/test").addInterceptors(interceptor).withSockJS();
    }

    @Bean
    public MyWebSocketHandler myWebSocketHandler(){
        return new MyWebSocketHandler();
    }
}
