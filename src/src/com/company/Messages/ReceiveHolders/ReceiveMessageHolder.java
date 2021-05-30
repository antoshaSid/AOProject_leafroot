package com.company.Messages.ReceiveHolders;

import com.company.Messages.IMessage;
import com.company.Messages.Message;

//Класс для получения сообщения в рантайме
public class ReceiveMessageHolder implements IMessage {

    private String from;
    private String to;
    private Message encryptedMessageForTo;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Message getMessageForTo() {
        return encryptedMessageForTo;
    }

    public ReceiveMessageHolder(){}

    public ReceiveMessageHolder(String from, String to, Message message) {
        this.from = from;
        this.to = to;
        this.encryptedMessageForTo = message;

    }

}
