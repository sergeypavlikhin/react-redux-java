package com.pavser.websocketlearn.redux;

import com.pavser.reduxj.DispatchableObject;

/**
 * Created by Sergey on 18.05.2017.
 */
public class MessageDispatchableObj implements DispatchableObject {

    private String action;
    private String message;

    public MessageDispatchableObj(String action, String message) {
        this.action = action;
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
