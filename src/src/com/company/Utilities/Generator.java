package com.company.Utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

//Генерит всякие ништяки
public final class Generator {

    public static String computeSHA256(String raw, String salt) throws NoSuchAlgorithmException {
        String data = raw + salt;
        return getHashedString(data);
    }

    public  static <K,V>String computeSHA256(HashMap<K,V> raw, String salt) throws NoSuchAlgorithmException {

        String data = "";
        for (HashMap.Entry<K, V> entry : raw.entrySet()) {
            if(entry.getKey().toString() != "hash")
                data = data.concat(entry.getValue().toString());
        }
        data += salt;
        return getHashedString(data);
    }

    private static String getHashedString(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

        return Convert.bytesToHexString(hash);
    }


    public static int getRandomMessageID(){
        return randomInt(100000000,999999999);
    }

    private static int randomInt(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

}
