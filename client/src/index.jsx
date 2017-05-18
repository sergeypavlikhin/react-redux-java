import React from "react";
import ReactDOM from "react-dom";
import {reducer} from './reducer';
import {List, Map} from 'immutable';

import * as action_creators from './action_creators';

import {createStore} from 'redux';
import {connect, Provider} from 'react-redux';

import {WSController} from './ws.js';

//Создадим хранилище
let store = createStore(reducer);

store.dispatch({
    type: "INIT",
    messages: List.of("Message_1", "Message_2", "Message_3")
});


function mapStateToProps(state) {
    console.log(state);
    return {
        messages: state.getIn(['messages'])
    };
}

class MainElement extends React.Component{
    constructor(props){
        super(props);
        this.state = {messages: ["Hi", "Hi2", "Hi3"]};
        this.clickHandler = this.clickHandler.bind(this);
        this.handleData = this.handleData.bind(this);
    }

    clickHandler(e){
        this.wsHolder.push("ivan");

    }
    handleData(data){
        let messageData = data.data;
        messageData = JSON.parse(messageData);
        store.dispatch(messageData);
    }
    componentDidMount(){
        console.log("Component did mount");
        this.wsHolder = new WSController(
            ()=>console.log('onopen'),
            ()=>console.log('onclose'),
            this.handleData
        );
    }

    render(){
        const elements = this.props.messages.map((entry) =>  {
            return <h1 key={entry}>{entry}</h1>;
        });
        return <div>{elements}</div>;
    }
}

MainElement = connect(mapStateToProps, action_creators)(MainElement);

ReactDOM.render(<Provider store={store}><MainElement/></Provider>, document.getElementById('app'));

