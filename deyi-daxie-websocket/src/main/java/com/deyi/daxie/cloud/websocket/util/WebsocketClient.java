package com.deyi.daxie.cloud.websocket.util;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deyi.daxie.cloud.websocket.domain.ParamVo;
import com.deyi.daxie.cloud.websocket.domain.SocketResultDto;
import com.deyi.daxie.cloud.websocket.service.WebSocketService;
import com.deyi.daxie.cloud.websocket.service.impl.WebSocketServiceImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;


/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/6/7
 */
public class WebsocketClient extends WebSocketClient {
    private static Logger logger = LoggerFactory.getLogger(WebsocketClient.class);

    private WebSocketSession session;

    private String deviceNum;

    public WebsocketClient(URI url, WebSocketSession session, String deviceNum) {
        super(url);
        this.session = session;
        this.deviceNum = deviceNum;
    }
    @Override
    public void onOpen(ServerHandshake shake) {
        // TODO Auto-generated method stub
        logger.info("握手...");
        for(Iterator<String> it=shake.iterateHttpFields();it.hasNext();) {
            String key = it.next();
            logger.info(key+":"+shake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String paramString) {
        // TODO Auto-generated method stub
        SocketResultDto result = new SocketResultDto();
        result.setMethod(ParamVo.OFF_LINE);
        logger.info("接收到消息："+paramString);
        JSONObject json = JSON.parseObject(paramString);
        Object code = json.get("code");
        if(code != null && StrUtil.equals(code+"", "0000")){
            result.setData("0000");
        }else {
            result.setData("1111");
        }
        try {
            session.sendMessage(new TextMessage(JSON.toJSONString(result)));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            WebSocketServiceImpl.sessionMap.remove(deviceNum);
            this.close();
        }

    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        // TODO Auto-generated method stub
        logger.info("关闭...");
    }

    @Override
    public void onError(Exception e) {
        // TODO Auto-generated method stub
        logger.info("异常"+e);

    }
}
