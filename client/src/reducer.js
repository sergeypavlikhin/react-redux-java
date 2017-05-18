import {init, addMessage} from './core.js';
import {Map} from 'immutable';
/**
 * Created by Sergey on 17.05.2017.
 * Обеспечит однозначную связь между state и action
 *
 */
export function reducer(state = Map(), action) {
    switch (action.type){
        case 'INIT': return init(state, action.messages);
        case 'ADD' : return addMessage(state, action.messages);
    }
}
