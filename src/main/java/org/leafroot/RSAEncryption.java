package org.leafroot;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

public class RSAEncryption {

    private KeyPair keyPair;
    private final String ALGORITHM = "RSA";

    public void initializeKey() throws NoSuchAlgorithmException, IOException {
        keyPair = generateKeyPair();
        SavePrivateKey();
    }

    public String getPublicKeyOnHex() {

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        return Convert.bytesToHexString(x509EncodedKeySpec.getEncoded());
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.genKeyPair();
    }


    private void SavePrivateKey() throws IOException, IOException {

        PrivateKey privateKey = keyPair.getPrivate();

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                privateKey.getEncoded());
        FileOutputStream fos = new FileOutputStream(Config.PRIVATE_KEY_PATH);
        fos.write(pkcs8EncodedKeySpec.getEncoded());
        fos.close();
    }

    public void loadKeyPair(String phoneNumber) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {


        byte[] encodedPublicKey = Convert.hexStringToBytes(UserUtilities.getUserByPhone(phoneNumber).key);


        File filePrivateKey = new File(Config.PRIVATE_KEY_PATH);
        FileInputStream fis = new FileInputStream(Config.PRIVATE_KEY_PATH);
        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();

        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        keyPair = new KeyPair(publicKey, privateKey);
    }

}
