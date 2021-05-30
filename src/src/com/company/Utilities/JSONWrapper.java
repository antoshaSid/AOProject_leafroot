package com.company.Utilities;

import com.google.gson.Gson;

//Конвертор JSON
public final class JSONWrapper {



    private static Gson json = new Gson();

    public static String encode(Object data){
        return json.toJson(data);
    }



    public static <T>T decode(String data, Class<T> type){

        var t = json.fromJson(data, type);

        return t;
    }

}
