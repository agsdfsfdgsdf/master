package com.deyi.daxie.cloud.vehicle.query.websocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Description: websocket配置类
 * @date 2022/9/20
 * @author Huang ShuYing
 */
//@Configuration
public class WebSocketConfig {
    /**
     * 给spring容器注入这个ServerEndpointExporter对象
     * 相当于xml：
     * <beans>
     * <bean id="serverEndpointExporter" class="org.springframework.web.socket.server.standard.ServerEndpointExporter"/>
     * </beans>
     * <p>
     * 检测所有带有@serverEndpoint注解的bean并注册他们。
     *
     * @return bean
     */
    @Bean
     public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
}
}