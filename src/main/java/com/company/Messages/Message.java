package com.company.Messages;

import com.company.Data.Config;
import com.company.Messages.Enums.MessageAuthor;
import com.company.Messages.Enums.MessageType;
import com.company.Utilities.Convert;

import java.time.LocalDateTime;

//Сообщение
public class Message {

    public int index;
    private String date;
    public MessageType messageType;
    public MessageAuthor sender;
    public String data;

    public String getDate(){
        return date;
    }

    public void setTimeNow() {
        date = Config.DATE_FORMAT.format(LocalDateTime.now());
    }

    public Message(MessageType messageType, String data, int index){
        this.index = index;
        this.messageType = messageType;
        this.data = data;
        setTimeNow();
    }

    public Message(MessageType messageType, byte[] data, int index){
        this.index = index;
        this.messageType = messageType;
        this.data = Convert.bytesToHexString(data);
        setTimeNow();
    }

    public Message(){

    }

    public Message(MessageType messageType, MessageAuthor sender, String data, int index){
        this.index = index;
        this.messageType = messageType;
        this.data = data;
        this.sender = sender;
        setTimeNow();
    }

    @Override
    public String toString(){
        return "index = " + index + "\ndate = " + date + "\ndate = " + data;
    }
}


