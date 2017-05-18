package com.pavser.websocketlearn.redux;

import com.pavser.reduxj.Reducer;

import java.util.List;

/**
 * Created by Sergey on 18.05.2017.
 */
public class MessageReducer implements Reducer<List<String>, MessageDispatchableObj> {
    @Override
    public List<String> reduce(List<String> strings, MessageDispatchableObj messageDispatchableObj) {
        strings.add(messageDispatchableObj.getMessage());
        return strings;
    }
}
