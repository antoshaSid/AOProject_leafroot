package com.company.Data;

import com.company.Messages.Message;
import com.company.Messages.Enums.MessageAuthor;
import com.company.Messages.ReceiveHolders.ReceiveMessageHolder;
import com.company.Utilities.JSONWrapper;

import java.io.*;

//Класс для кэширования данных
public final class Caching {



    public static void storeReceived(ReceiveMessageHolder receiveMessageHolder) throws IOException {
        Message message = receiveMessageHolder.getMessageForTo();
        message.sender = MessageAuthor.Him;
        String chatFilePath = Config.CHAT_CACHE_PATH + "\\" + receiveMessageHolder.getFrom() + ".chat";
        store(message,chatFilePath);
    }

    private static void store(Message message,String chatFilePath) throws IOException {


        File chatFile = new File(chatFilePath);
        if(!chatFile.exists())
            chatFile.createNewFile();

        try (FileWriter f = new FileWriter(chatFilePath, true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            p.println(JSONWrapper.encode(message));
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public static void loadMissedMessages()
    {

    }

}
