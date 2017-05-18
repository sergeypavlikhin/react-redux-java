/**
 * Created by Sergey on 18.05.2017.
 */
export function init(entries) {
    return {
        type: "INIT",
        entries
    };
}
export function add(message) {
    return {
        type: "ADD",
        message
    };
}