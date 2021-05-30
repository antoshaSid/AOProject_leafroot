package org.leafroot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PrimitiveIterator;

public final class Convert {

    public static <K, V> String hashMapToPostString(HashMap<K, V> hashMap) {
        String result = "";
        for (HashMap.Entry<K, V> entry : hashMap.entrySet()) {
            result = result.concat(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return result;
    }

    public static String bytesToHexString(byte[] raw) {
        StringBuilder sb = new StringBuilder();
        for (byte b : raw)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException(
                    "Invalid hexadecimal String supplied.");
        }

        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    private static byte hexToByte(String hexString) {
        int firstDigit = Character.digit(hexString.charAt(0), 16);
        int secondDigit = Character.digit(hexString.charAt(1), 16);
        return (byte) ((firstDigit << 4) + secondDigit);
    }

}
