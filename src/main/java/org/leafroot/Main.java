package org.leafroot;


import java.net.Socket;
import java.net.URI;

public class Main {

    public static void main(String[] args) throws Exception {

        final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://91.228.154.50:2346"));
        clientEndPoint.addMessageHandler(message -> System.out.println(message));

        while (true) {
            clientEndPoint.sendMessage(new Message("","", "sadfsafasfs"));
            Thread.sleep(3000);
        }
    }
}