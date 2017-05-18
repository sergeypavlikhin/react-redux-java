package com.pavser.websocketlearn;

import com.pavser.websocketlearn.action.Action;
import com.pavser.websocketlearn.action.ActionType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Sergey on 16.05.2017.
 */

public class Program{

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

    public static void main(String[] args) throws Exception {
        serverThread.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       String inputtedText;

        while (!(inputtedText = reader.readLine()).equals("exit")){
            //Send all inputted text
            System.out.println("Inputted text: " + inputtedText);

            wsHandler.doAction(
                    new Action(
                            ActionType.ADD.toString(),
                            Arrays.asList(inputtedText))
            );
        }
        System.exit(0);
    }

}
