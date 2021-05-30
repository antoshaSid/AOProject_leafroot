package com.company.Utilities;

import com.company.Data.Config;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

//Методя для удобной работы с RSA
public class RSAEncryption {

    private KeyPair keyPair;
    private final String ALGORITHM = "RSA";

    public void initializeKey(){
        keyPair = generateKeyPair();
        SavePrivateKey();
    }

    public String getPublicKeyOnHex() {

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        return Convert.bytesToHexString(x509EncodedKeySpec.getEncoded());
    }

    public static KeyPair generateKeyPair(){
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.genKeyPair();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void SavePrivateKey(){

        try{
            PrivateKey privateKey = keyPair.getPrivate();

            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                    privateKey.getEncoded());
            FileOutputStream fos = new FileOutputStream(Config.PRIVATE_KEY_PATH);
            fos.write(pkcs8EncodedKeySpec.getEncoded());
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadKeyPair(String phoneNumber){

        try{
            File filePrivateKey = new File(Config.PRIVATE_KEY_PATH);
            FileInputStream fis = new FileInputStream(Config.PRIVATE_KEY_PATH);
            byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
            fis.read(encodedPrivateKey);
            fis.close();

            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey publicKey = Convert.stringToPublicRSAKey(UserUtilities.getUserByPhone(phoneNumber).key);

            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            keyPair = new KeyPair(publicKey, privateKey);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public String encode(String publicKey, String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, Convert.stringToPublicRSAKey(publicKey));
            return Convert.bytesToHexString(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decode(String data) {

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            return new String(cipher.doFinal(Convert.hexStringToBytes(data)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
