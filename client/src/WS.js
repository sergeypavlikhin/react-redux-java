/**
 * Created by Sergey on 17.05.2017.
 */
export class WSController {

    constructor(open, close, onmessage){
        this.address = "ws://localhost:8081/";
        this.ws = new WebSocket(address);
        this.ws.onopen = open;
        this.ws.onclose = close;
        this.ws.onmessage = onmessage;
        this.push = this.push.bind(this);
        this.close = this.close.bind(this);
    }

    push(message){
        this.ws.send(message);
    }
    close(){
        this.ws.close();
    }
}
