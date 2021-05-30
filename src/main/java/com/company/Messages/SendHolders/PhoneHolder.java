package com.company.Messages.SendHolders;

import com.company.Messages.IMessage;

//Класс для отправки серверу телефона
public class PhoneHolder implements IMessage {

    private String phone;

    public PhoneHolder(){}

    public PhoneHolder(String phone) {
        this.phone = phone;
    }
}
