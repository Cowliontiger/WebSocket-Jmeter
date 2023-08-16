package com.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.apache.log4j.Logger;

import java.net.URI;

public class WebSocketUtil extends WebSocketClient {

    Logger logger = Logger.getLogger(WebSocketUtil.class);

    public WebSocketUtil(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        logger.info("------ MyWebSocket onOpen ------");
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        logger.info("------ MyWebSocket onClose ------");
    }

    @Override
    public void onError(Exception arg0) {
        logger.info("------ MyWebSocket onError ------");
    }

    @Override
    public void onMessage(String arg0) {
        logger.info("-------- 接收到服务端数据： " + arg0 + "--------");
    }
}