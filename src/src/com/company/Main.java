package com.company;


import com.company.Client.Authorization;
import com.company.Messages.*;
import com.company.Client.ClientSocket;
import com.company.Messages.Enums.Command;
import com.company.Messages.Enums.MessageType;
import com.company.Messages.SendHolders.SendMessageHolder;
import com.company.Utilities.ChatUtilities;
import com.company.Utilities.Generator;
import com.company.Utilities.UserUtilities;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        ChatUtilities.getAllChatPhones();
        String login = "";
        String password = "";
        if(UserUtilities.getUserByPhone(login) == null)
            Authorization.signUp(login,"name", "surname", "password", "desc");
        else if (!Authorization.loadSession())
            Authorization.logIn(login, password);


        while (true)
        {
            var m = new SendMessageHolder("380507128080","380507128080",new Message(MessageType.Text,new Scanner(System.in).nextLine(),Generator.getRandomMessageID()));
            ClientSocket.send(MessageWrapperFactory.createSendMessageWrapper(m));
        }
//        ClientSocket.closeConnection();

    }
}