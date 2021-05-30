package com.company.Utilities;

import com.company.Data.Config;

import java.io.File;
import java.util.ArrayList;

//Методя для удобной работы с контактами
public final class ChatUtilities {

    public static String[] getAllChatPhones(){
        File chatsPath = new File(Config.CHAT_CACHE_PATH);
        String contents[] = chatsPath.list();
        if (contents != null) {
            for (int i = 0; i < contents.length; i++) {
                contents[i] = contents[i].replace(".chat", "");
            }
        }
        return contents;
    }

}
