package com.deyi.daxie.cloud.websocket.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.deyi.daxie.cloud.websocket.domain.ParamVo;
import com.deyi.daxie.cloud.websocket.domain.SessionCache;
import com.deyi.daxie.cloud.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/3/29
 */
@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {

    private static ConcurrentHashMap<String, SessionCache> sessions = new ConcurrentHashMap<>();

    private static AtomicInteger onlineNum = new AtomicInteger(0);

    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount(){
        onlineNum.decrementAndGet();
    }

    @Autowired
    private WebSocketService service;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String dataStr = message.getPayload();

        ParamVo paramVo = JSON.parseObject(dataStr, ParamVo.class);
        sessions.get(session.getId()).setDeviceNum(paramVo.getDeviceNum());
        paramVo.setSession(session);
        switch (paramVo.getMethod()){
            case ParamVo.HOME:
                service.home(paramVo);
                break;
            case ParamVo.LOCATION:
                service.location(paramVo);
                break;
            case ParamVo.DRIVER_NUM:
                service.driverNum(paramVo);
                break;
            case ParamVo.OFF_LINE:
                service.offline(paramVo);
        }

        log.info(String.format("========================================================收到用户：【%s】发来的【%s】", sessions.get(session.getId()).getUserName(), message.getPayload()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object loginName =  session.getAttributes().get("loginName");
        Object loginId =  session.getAttributes().get("loginId");
        String username = "";
        if(loginName != null){
            username = loginName.toString();
        }
        Long userId = null;
        if(loginId != null){
            userId = Long.parseLong(loginId+"");
        }
        SessionCache cache = sessions.put(session.getId(), new SessionCache(session.getId(), userId, username, "", session));
        if(cache == null){
            addOnlineCount();
        }
        session.sendMessage(new TextMessage("websocket连接建立"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) throws Exception {
        String sessionId = webSocketSession.getId();
        Long userId = sessions.get(sessionId).getUserId();
        sessions.remove(sessionId);
        subOnlineCount();
    }

    public static ConcurrentHashMap<String, SessionCache> getSessions() {
        return sessions;
    }


}

