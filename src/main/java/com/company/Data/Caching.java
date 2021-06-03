package com.company.Data;

import com.company.Messages.Message;
import com.company.Messages.Enums.MessageAuthor;
import com.company.Messages.ReceiveHolders.ReceiveMessageHolder;
import com.company.Messages.SendHolders.SendMessageHolder;
import com.company.Utilities.JSONWrapper;
import com.company.pages.main_page.Chat;
import com.company.pages.main_page.MessengerFrame;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

//Класс для кэширования данных
public final class Caching {



    public static void storeReceived(ReceiveMessageHolder receiveMessageHolder) throws IOException {
        Message message = receiveMessageHolder.getMessageForTo();
        message.sender = MessageAuthor.Him;
        String chatFilePath = Config.CHAT_CACHE_PATH + "/" + receiveMessageHolder.getFrom() + ".chat";
        store(message,chatFilePath);
    }

    public static void storeSent(SendMessageHolder sendMessageHolder) throws IOException {
        Message message = sendMessageHolder.getMessageForFrom();
        message.sender = MessageAuthor.Me;
        String chatFilePath = Config.CHAT_CACHE_PATH + "/" + sendMessageHolder.getTo() + ".chat";
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

    public static void loadFromCache(DefaultListModel<String> chatList, Map<String, Chat> userChatsMap, UserData userData){
        File cachePath = Config.CHAT_CACHE_PATH.toFile();
        String[] chats = cachePath.list();
        if (chats != null)
            for(int i = 0; i < chats.length; i++) {
                if (chats[i].endsWith(".chat")) {
                    chats[i] = chats[i].replace(".chat","");
                    chatList.addElement(chats[i]);
                    userChatsMap.put(chats[i], new Chat(userData.phone, chats[i], Caching.loadChatHistory(chats[i])));
                }
            }
    }

    public static ArrayList<Message> loadChatHistory(String chatID){

        ArrayList<Message> messages = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(Config.CHAT_CACHE_PATH + "/" + chatID + ".chat"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                messages.add(JSONWrapper.decode(line, Message.class));
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        return messages;
    }

    public static void loadMissedMessages(){

    }

}