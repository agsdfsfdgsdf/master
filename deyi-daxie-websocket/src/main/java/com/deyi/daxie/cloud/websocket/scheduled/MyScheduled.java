package com.deyi.daxie.cloud.websocket.scheduled;

import com.deyi.daxie.cloud.websocket.domain.ParamVo;
import com.deyi.daxie.cloud.websocket.domain.SessionCache;
import com.deyi.daxie.cloud.websocket.handler.MyWebSocketHandler;
import com.deyi.daxie.cloud.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/6/6
 */
@Slf4j
@Component
public class MyScheduled {

    @Autowired
    private WebSocketService service;

    @Scheduled(fixedDelay = 10000)
    public void location(){
        Collection<SessionCache> values = MyWebSocketHandler.getSessions().values();
        for (SessionCache session : values) {
            ParamVo vo = new ParamVo();
            vo.setSession(session.getSession());
            vo.setDeviceNum(session.getDeviceNum());
//            service.home(vo);
            service.location(vo);
        }
    }
}
