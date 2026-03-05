package encryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES_Encryption{

    //encryption method
    public static void encrypt(String file, String writePath, SecretKey key) throws Exception{
        try {
            // reads from text file
            Path filePath = Paths.get(file);
            String text = Files.readString(filePath);

            // creates a cipher, must be same key as decrypt cipher
            Cipher encryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = encryptCipher.doFinal(text.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            
            // writes to text file
            // creates new file or overwrites current file
            Files.writeString(Path.of(writePath), encryptedText);
            System.out.println("Successfully encrypted file.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            throw e;
        }

    }

    // decryption method
    public static void decrypt(String file, String writePath, SecretKey key) throws Exception{
        try {
            // reads from text file
            Path filePath = Paths.get(file);
            String text = Files.readString(filePath);

            // creates a cipher, needs to be same key as encrypt cipher
            Cipher decryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = Base64.getDecoder().decode(text);
            byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);
            String decryptedText = new String(decryptedBytes);
            
            // writes to text file
            // creates new file or overwrites current file
            Files.writeString(Path.of(writePath), decryptedText);
            System.out.println("Successfully decrypted file.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            throw e;
        }
    }

    // main method written for testing purposes
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Requires command line arguments: <input> <encryptedOutput> <decryptedOutput>");
            return;
        }
        try {
            SecretKey key = generateKey();
            encrypt(args[0], args[1], key);
            decrypt(args[1], args[2], key);
        } catch (Exception e) {
            System.err.println("Program Failed");
            e.printStackTrace();
        }   
    }

    // generates encryption key
    public static SecretKey generateKey() throws Exception{
        KeyGenerator gen = KeyGenerator.getInstance("DES");
        // 56 bit key
        gen.init(56);
        SecretKey key = gen.generateKey();
        return key;
    }
}
