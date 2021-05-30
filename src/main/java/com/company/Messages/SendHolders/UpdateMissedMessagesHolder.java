package com.company.Messages.SendHolders;

import com.company.Messages.IMessage;
import com.company.Utilities.ChatUtilities;
import com.company.Utilities.MessageUtilities;

import java.util.HashMap;

//Класс для проверки на пропущенные сообщения
public class UpdateMissedMessagesHolder implements IMessage {
    private String phone;
    private HashMap<String, Integer> contactLastMessage;

    public UpdateMissedMessagesHolder(){}

    public UpdateMissedMessagesHolder(String phone){
        this.phone = phone;

        String[] phones = ChatUtilities.getAllChatPhones();
        contactLastMessage = new HashMap<>();
        if (phones != null) {
            for (int i = 0; i < phones.length; i++)
                contactLastMessage.put(phones[i], MessageUtilities.getLastMessageOfUser(phones[i]).index);
        }
    }

}
