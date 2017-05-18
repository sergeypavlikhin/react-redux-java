package com.pavser.websocketlearn;

import com.pavser.reduxj.Store;
import com.pavser.websocketlearn.redux.MessageDispatchableObj;
import com.pavser.websocketlearn.redux.MessageReducer;
import com.pavser.websocketlearn.redux.MessageSubcriber;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 16.05.2017.
 */

public class Program {


    public static void main(String[] args) throws Exception {

        MessageSubcriber subcriber = new MessageSubcriber();

        Store<List<String>, MessageDispatchableObj> store = Store.createStore(new MessageReducer(), new ArrayList<>());
        store.addSubscriber(subcriber);

        subcriber.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputtedText;

        while (!(inputtedText = reader.readLine()).equals("exit")) {
            //Send all inputted text
            System.out.println("Inputted text: " + inputtedText);

            store.dispatch(new MessageDispatchableObj("ADD", inputtedText));
        }
        System.exit(0);
    }

}
