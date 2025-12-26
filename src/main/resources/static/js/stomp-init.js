const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/webchat-endpoint'
});

stompClient.onWebSocketError = (error) => {
    console.error('onWebSocketError: Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('onStompError: Broker reported error: ' + frame.headers['message']);
    console.error('onStompError: Additional details: ' + frame.body);
};