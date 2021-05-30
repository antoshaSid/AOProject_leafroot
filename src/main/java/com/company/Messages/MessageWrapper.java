package com.company.Messages;

import com.company.Messages.Enums.Command;
import com.company.Utilities.JSONWrapper;

//Обертка комнады и сообщения
public class MessageWrapper {

    public Command command;
    public String message;

    public MessageWrapper(){

    }

    public MessageWrapper(Command command, IMessage message) {
        this.command = command;
        this.message = JSONWrapper.encode(message);
    }

}




