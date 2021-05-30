package com.company.Data;

import com.company.EventHolders.IMessageReceived;
import com.company.Messages.Message;
import com.company.Messages.ReceiveHolders.ReceiveMessageHolder;
import com.company.Utilities.RSAEncryption;

import java.util.ArrayList;

//Наш пользователь
public final class User {

    public static UserData data;
    public static RSAEncryption rsa;
    private static ArrayList<IMessageReceived> onMessageReceived;

    public static void addOnMessageReceivedHandler(IMessageReceived handler)
    {
        if(onMessageReceived == null)
            onMessageReceived = new ArrayList<>();
        onMessageReceived.add(handler);
    }

    public static void invokeOnMessageReceived(ReceiveMessageHolder message)
    {
        for (int i = 0; i < onMessageReceived.size(); i++)
            onMessageReceived.get(i).Receive(message);
    }



}
