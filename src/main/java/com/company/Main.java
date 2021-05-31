package com.company;


import com.company.Client.Authorization;
import com.company.Messages.*;
import com.company.Client.ClientSocket;
import com.company.Messages.Enums.MessageType;
import com.company.Messages.SendHolders.SendMessageHolder;
import com.company.Utilities.ChatUtilities;
import com.company.Utilities.Generator;
import com.company.Utilities.UserUtilities;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

//        ChatUtilities.getAllChatPhones();
        String login = "380500757695";
        String password = "1q2w3e4r5t";
        if(UserUtilities.getUserByPhone(login) == null)
            Authorization.signUp(login,"name", "surname", password, "desc");
        else if (!Authorization.loadSession())
            Authorization.logIn(login, password);


        while (true)
        {
            var m = new SendMessageHolder(login,"387",new Message(MessageType.Text,new Scanner(System.in).nextLine(),Generator.getRandomMessageID()));
            ClientSocket.send(MessageWrapperFactory.createSendMessageWrapper(m));
        }


//        ClientSocket.closeConnection();

    }
}