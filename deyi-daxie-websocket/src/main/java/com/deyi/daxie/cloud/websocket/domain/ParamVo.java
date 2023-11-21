package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

/**
 * Description: websocket 消息类型
 *
 * @author Chen Xu
 * @date 2023/6/5
 */
@Data
public class ParamVo {
    public final static String HOME = "home";
    public final static String LOCATION = "location";
    public static final String DRIVER_NUM = "deviceNum";
    public static final String OFF_LINE = "offline";

    private String method;
    private String deviceNum;
    private WebSocketSession session;
}
