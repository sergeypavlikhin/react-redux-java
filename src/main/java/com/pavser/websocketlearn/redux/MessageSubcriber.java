package com.pavser.websocketlearn.redux;

import com.pavser.reduxj.Store;
import com.pavser.reduxj.Subscriber;
import com.pavser.websocketlearn.WSHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;

import java.util.List;

/**
 * Created by Sergey on 18.05.2017.
 */
public class MessageSubcriber implements Subscriber<List<String>> {


    private static WSHandler wsHandler = null;

    private final static Thread serverThread = new Thread(new Runnable() {
        public void run() {
            Server server = new Server(8081);
            wsHandler = new WSHandler();
            wsHandler.setHandler(new DefaultHandler());
            server.setHandler(wsHandler);
            try {
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    public void start(){
        serverThread.start();
    }

    @Override
    public void notice(Store<List<String>, ?> store) {
        List<String> messages = store.getState();
        wsHandler.doAction(messages);
    }
}
