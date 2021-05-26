package com.company;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

public final class UserUtilities {

    public static UserData getUserByPhone(String userID) throws IOException {

        var postData = new HashMap<String, String>() {{
            put("id", String.valueOf(userID));
        }};

        var postResponse = ServerRequests.post(Config.SERVER_GET_USER,Convert.hashMapToPostString(postData));
        return new Gson().fromJson(postResponse,UserData.class);
    }

}
