package com.deyi.daxie.cloud.vehicle.query.websocket;

import com.deyi.daxie.cloud.vehicle.query.service.impl.MessageListen;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author Huang ShuYing
 * @date 2022/9/20
 */
@ServerEndpoint(value = "/ws/location")
//@Component
public class WebSocketServer {

    private static final Log log = LogFactory.getLog(WebSocketServer.class);

    @PostConstruct
    public void init() {
        log.info("webSocket download");
    }

    private static final AtomicInteger  ONLINE_COUNT= new AtomicInteger(0);

    /**
     * Description: oncurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
     * @date 2022/9/20
     */
    private static final CopyOnWriteArraySet<Session>  SESSION_SET= new CopyOnWriteArraySet<>();

    /**
     * Description: 连接建立成功调用的方法
     * @date 2022/9/20
     * @author Huang ShuYing
     */
    @OnOpen
    public void onOpen(Session session) {
        SESSION_SET.add(session);
        log.info("link success..." );
    }

    /**
     * Description: 连接关闭调用的方法
     *
     * @date 2022/9/20
     * @author Huang ShuYing
     */
    @OnClose
    public void onClose(Session session) {
        SESSION_SET.remove(session);
        int cnt = ONLINE_COUNT.decrementAndGet();
        log.info("have link close，link count:" + cnt);
    }

    /**
     * Description: 收到客户端消息后调用的方法
     *
     * @date 2022/9/20
     * @author Huang ShuYing
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("have new message from client:" + message);
        MessageListen messageListen=new MessageListen();
        messageListen.messageListen(message);
    }

    /**
     * Description: 出现错误
     *
     * @date 2022/9/20
     * @author Huang ShuYing
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("error Session ID:" + error.getMessage() + "\n" + session.getId());
        error.printStackTrace();
    }

    /**
     * Description: 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @date 2022/9/20
     * @author Huang ShuYing
     */
    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("sent error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Description: 群发消息
     *
     * @date 2022/9/20
     * @author Huang ShuYing
     */
    public static void broadCastInfo(String message)  {
        for (Session session : SESSION_SET) {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        }
    }

    /**
     * Description: 指定Session发送消息
     *
     * @date 2022/9/20
     * @author Huang ShuYing
     */
    public static void sendMessage(String message, String sessionId) {
        Session session = null;
        for (Session s : SESSION_SET) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            sendMessage(session, message);
        } else {
            log.info("Can`t find appoint id:" + sessionId);
        }
    }
}
