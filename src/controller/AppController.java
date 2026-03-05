package controller;


import database.EncryptionDAO;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import encryption.DES_Encryption;

public class AppController {

    private EncryptionDAO dao;

    public AppController() {
        dao = new EncryptionDAO();
    }

    public void encryptFile(String inputPath, String outputPath) throws Exception {

        // 1. Generate key
        SecretKey key = DES_Encryption.generateKey();

        // 2. Encrypt file
        DES_Encryption.encrypt(inputPath, outputPath, key);

        // 3. Convert key to Base64 string for database
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());

        // 4. Store in database
        dao.insertRecord(inputPath, null, outputPath, encodedKey);
    }

    public void decryptFile(String encryptedPath, String outputPath, String encodedKey) throws Exception {

        // Convert Base64 string back to SecretKey
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");

        DES_Encryption.decrypt(encryptedPath, outputPath, key);
    }
}