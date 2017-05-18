package com.pavser.websocketlearn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pavser.websocketlearn.action.Action;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Sergey on 16.05.2017.
 */
public class WSHandler extends WebSocketHandler {

    private final Set<ChatWebSocket> webSockets = new CopyOnWriteArraySet<ChatWebSocket>();

    public WebSocket doWebSocketConnect(HttpServletRequest httpServletRequest, String s) {
        return new ChatWebSocket();
    }

    public void doAction(Action action){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        for (ChatWebSocket webSocket: webSockets){
            webSocket.sendMessage(gson.toJson(action));
        }
    }

    private class ChatWebSocket implements WebSocket.OnTextMessage {

        /**
         * Store connection
         */
        private Connection connection;



        public void sendMessage(String message){
            try {
                connection.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void onOpen(Connection connection) {
            // Сохраняем соединение в свойство ChatWebSocket::connection
            this.connection = connection;
            System.out.println("Open... ");
            webSockets.add(this);
        }

        public void onMessage(String data) {
            System.out.println("New message: " + data);
            try {
                connection.sendMessage(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void onClose(int closeCode, String message) {
            webSockets.remove(this);
            System.out.println("Close... ");
        }
    }
}
