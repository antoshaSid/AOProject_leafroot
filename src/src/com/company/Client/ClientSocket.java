package com.company.Client;

import com.company.Server.WebsocketClient;
import com.company.Utilities.JSONWrapper;
import com.company.Messages.SendHolders.PhoneHolder;
import com.company.Messages.Enums.Command;
import com.company.Messages.MessageWrapper;
import com.company.Data.User;

import java.net.URI;
import java.net.URISyntaxException;

//Класс для общения с сервером
public final class ClientSocket {

    private static WebsocketClient clientEndPoint;

    public static void initialize(String ip, String port, String phone) throws URISyntaxException, InterruptedException {
        clientEndPoint = new WebsocketClient(new URI("ws://" + ip + ":" + port));
        ClientSocket.send(new MessageWrapper(Command.AddUser,new PhoneHolder(phone)));
    }

    public static void send(MessageWrapper data) {
        if (clientEndPoint == null)
            throw new NullPointerException();

       clientEndPoint.sendMessage(JSONWrapper.encode(data));
    }

    public static void closeConnection() {
        send(new MessageWrapper(Command.RemoveUser,new PhoneHolder(User.data.phone)));
    }
}
