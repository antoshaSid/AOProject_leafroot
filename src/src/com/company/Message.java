package com.company;

public class Message {

    public String sender;
    public String getterChatID;
    public String data;

    public Message(String sender, String getterChatID, String data) {
        this.sender = sender;
        this.getterChatID = getterChatID;
        this.data = data;
    }
}
