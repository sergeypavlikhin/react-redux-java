package com.pavser.websocketlearn.action;

import java.util.List;

/**
 * Created by Sergey on 18.05.2017.
 */
public class Action {

    private String type;
    private List<String> messages;

    public Action(String type, List<String> messages) {
        this.type = type;
        this.messages = messages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
