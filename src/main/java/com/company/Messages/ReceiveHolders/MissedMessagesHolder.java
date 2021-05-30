package com.company.Messages.ReceiveHolders;

import com.company.Messages.IMessage;

//Класс для получения пропуущенных сообщений
public class MissedMessagesHolder implements IMessage {

    public String from;
    public String[] messages;

    public MissedMessagesHolder(){}

}
