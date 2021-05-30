package org.leafroot;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

public final class Authorization {


    public static void signUp() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {

        User.rsa = new RSAEncryption();
        User.rsa.initializeKey();
        var values = new HashMap<String, String>() {{
            put("phone", "380507128080");
            put("name", "Albert");
            put("surname", "Avahian");
            put("key", User.rsa.getPublicKeyOnHex());
            put("password", Generator.computeSHA256("Avahian",""));
            put("description", "Ебу нахуй я добавил это поле, но да похуй");
        }};
        values.put("hash",Generator.computeSHA256(values,Config.SALT));

        ServerRequests.post(Config.SERVER_REGISTRATION_URL,Convert.hashMapToPostString(values));
    }

    public static void logIn(String phone, String password) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {

        User.userData = UserUtilities.getUserByPhone(phone);
        if(Generator.computeSHA256(password,"") == User.userData.password)
            User.rsa.loadKeyPair(User.userData.phone);
        System.out.println(User.userData.toString());
    }

}
