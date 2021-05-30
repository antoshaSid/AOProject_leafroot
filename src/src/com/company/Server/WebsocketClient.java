package com.company.Server;

import com.company.Messages.Enums.MessageHandlerType;
import com.company.Utilities.JSONWrapper;
import com.company.Messages.MessageHandlerFactory;
import com.company.Messages.MessageWrapper;

import java.io.IOException;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


//Сокет для оббщения с сервером по TCP
@ClientEndpoint
public class WebsocketClient {

    Session userSession = null;
    private MessageHandler messageHandler;

    public WebsocketClient(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        MessageWrapper decodedMessage = JSONWrapper.decode(message, MessageWrapper.class);
        switch (decodedMessage.command) {
            case Receive -> this.messageHandler = MessageHandlerFactory.messageHandlers.get(MessageHandlerType.Receive);
            case UpdateMissed -> this.messageHandler = MessageHandlerFactory.messageHandlers.get(MessageHandlerType.UpdateMissed);
        }

        if (this.messageHandler != null)
            this.messageHandler.handleMessage(decodedMessage);
    }

    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    public interface MessageHandler {
        void handleMessage(MessageWrapper message) throws IOException;
    }

}