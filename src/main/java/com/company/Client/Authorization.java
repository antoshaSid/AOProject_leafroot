package com.company.Client;

import com.company.Data.Config;
import com.company.Data.User;
import com.company.Data.UserData;
import com.company.Messages.MessageWrapperFactory;
import com.company.Messages.SendHolders.UpdateMissedMessagesHolder;
import com.company.Server.ServerRequests;
import com.company.Utilities.Convert;
import com.company.Utilities.Generator;
import com.company.Utilities.RSAEncryption;
import com.company.Utilities.UserUtilities;

import java.io.*;
import java.net.URISyntaxException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Scanner;

//Класс отвечает за авторизацию пользователя и востановление сессии
public final class Authorization {


    //Регстрация
    public static void signUp(String phone, String name, String surname, String password, String description) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, URISyntaxException, InterruptedException {

        User.rsa = new RSAEncryption();
        User.rsa.initializeKey();
        var values = new HashMap<String, String>() {{
            put("phone", phone);
            put("name", name);
            put("surname", surname);
            put("key", User.rsa.getPublicKeyOnHex());
            put("password", Generator.computeSHA256(password,""));
            put("description", description);
        }};
        values.put("hash",Generator.computeSHA256(values, Config.SALT));

        ServerRequests.post(Config.SERVER_REGISTRATION_URL, Convert.hashMapToPostString(values));
        loadUser(phone);
        createSession();
    }

    //Вход
    public static void logIn(String phone, String password) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, URISyntaxException, InterruptedException {

        UserData tempData = UserUtilities.getUserByPhone(phone);
        if(!Generator.computeSHA256(password,"").equals(tempData.password)) {
            System.out.println("Wrong Password");
            return;
        }
        loadUser(phone);
        createSession();
    }

    //Создание сессии
    private static void createSession(){
        createCacheFolderIfNeeded();
        File session = new File(Config.SESSION_PATH);
        if(!session.exists()) {
            try {
                session.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter f = new FileWriter(Config.SESSION_PATH, true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            p.println(User.data.phone);
            p.println(generateSessionToken(User.data.phone,UserUtilities.getDeviceID()));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    //Загрузка сессии
    public static boolean loadSession()
    {

        String phone = "";
        String token = "";
        File file = new File(Config.SESSION_PATH);

        if(!file.exists())
            return false;

        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(reader.hasNextLine())
            phone = reader.nextLine();
        if(reader.hasNextLine())
            token = reader.nextLine();
        reader.close();
        if(token.equals(generateSessionToken(phone, UserUtilities.getDeviceID())))
        {
            loadUser(phone);
            return true;
        }
        return false;
    }

    //генерация токена сессии
    private static String generateSessionToken(String phone, String deviceID){
        try {
            return Generator.computeSHA256(phone, deviceID + Config.SALT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Загрузка пользователя и подклбчение к серверу с запросом на пропущенные сообщения
    private static void loadUser(String phone)
    {
        try {
            ClientSocket.initialize(Config.SERVER_IP,Config.SERVER_PORT,phone);
            ClientSocket.send(MessageWrapperFactory.createUpdateMissedMessageWrapper(new UpdateMissedMessagesHolder(phone)));
            User.data = UserUtilities.getUserByPhone(phone);
            RSAEncryption rsaEncryption = new RSAEncryption();
            rsaEncryption.loadKeyPair(User.data.phone);
            User.rsa = rsaEncryption;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Создания всяких папаок для кэша если надо
    public static void createCacheFolderIfNeeded() {

        File cacheFolder = new File(Config.CACHE_PATH);
        if (!cacheFolder.exists()) {
            cacheFolder.mkdirs();
        }

        File chatCacheFolder = new File(Config.CHAT_CACHE_PATH);
        if (!chatCacheFolder.exists()) {
            chatCacheFolder.mkdirs();
        }

    }
}
