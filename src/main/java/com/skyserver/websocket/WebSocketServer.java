package com.skyserver.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WebSocketServer
 * @Author shuai
 * @create 2023/8/10 15:05
 * @Instruction websocket服务端
 */
@Component
@ServerEndpoint("/ws/{sid}")//标识当前类处理websocket请求
@Slf4j
public class WebSocketServer {


    /**
     * 只要连接没有断开，session就只有一个，可以通过map集合保存
     */
    private static Map<String,Session> sessionMap=new HashMap<>();

    @OnOpen//连接建立时触发
    public void open(Session session,@PathParam("sid") String sid){//同时标注路径参数
        log.info("连接建立：{}",sid);
        sessionMap.put(sid,session);
    }

    @OnMessage//接受到服务端消息时触发
    public void onMessage(Session session,String message,@PathParam("sid") String sid){
        log.info("接受到消息：{}",message);
    }

    @OnClose//连接关闭时触发
    public void close(Session session,@PathParam("sid") String sid){
        log.info("连接关闭");
        sessionMap.remove(sid);
    }

    @OnError//通信异常时触发
    public void error(Session session,@PathParam("sid") String sid,Throwable throwable){
        log.info("通信异常");
        throwable.printStackTrace();//可以输出异常信息
    }


    /**
     * 给所有会话的客户端发消息
     */
    public void sendMessageToAll(String message) throws IOException {
        Collection<Session> sessions = sessionMap.values();
        if(!CollectionUtils.isEmpty(sessions)){
            for (Session session:sessions
                 ) {
                session.getBasicRemote().sendText(message);//session发消息的方法
            }
        }
    }
}
