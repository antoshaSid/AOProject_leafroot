package com.company.Utilities;

import com.company.Data.Config;
import com.company.Messages.Message;
import com.company.Messages.MessageWrapper;
import com.company.Server.ServerRequests;
import com.company.Data.UserData;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;

//Методя для удобной работы с юезрами
public final class UserUtilities {

    public static UserData getUserByPhone(String userID) throws IOException {

        var postData = new HashMap<String, String>() {{
            put("id", String.valueOf(userID));
        }};

        var postResponse = ServerRequests.post(Config.SERVER_GET_USER, Convert.hashMapToPostString(postData));

        var user = new Gson().fromJson(postResponse, UserData.class);
        return user;
    }

    public static String getDeviceID() {
        StringBuilder builder = new StringBuilder();
        Enumeration<NetworkInterface> nis = null;
        try {
            nis = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (nis.hasMoreElements()) {
            NetworkInterface ni = nis.nextElement();
            builder.append(ni.getName() + ni.getDisplayName());
        }

        return builder.toString();
    }


}
