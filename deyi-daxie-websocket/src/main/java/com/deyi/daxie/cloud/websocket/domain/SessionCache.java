package com.deyi.daxie.cloud.websocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

/**
 * Description: session
 *
 * @author Chen Xu
 * @date 2023/6/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionCache {

    private String sessionId;
    private Long userId;
    private String userName;
    private String deviceNum;
    private WebSocketSession session;

}
