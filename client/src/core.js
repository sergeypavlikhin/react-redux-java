/**
 * Created by Sergey on 17.05.2017.
 */
export function init(state, entries) {
    return state.set('messages', entries);
}
export function addMessage(state, message) {
    let funcAdd = list => list.push(message);
    if(message instanceof Array){
        funcAdd = list => list.push(...message);
    }
    let newState = state.updateIn(['messages'], funcAdd);

    return newState;
}