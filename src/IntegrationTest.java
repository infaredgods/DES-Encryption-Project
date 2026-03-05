import controller.AppController;
import database.DatabaseManager;
import database.EncryptionDAO;
import model.EncryptionRecord;

import java.util.List;

public class IntegrationTest {

    public static void main(String[] args) {

        try {

            // 1️⃣ Initialize database
            DatabaseManager.initializeDatabase();

            // 2️⃣ Create controller
            AppController controller = new AppController();

            // 3️⃣ Define file paths
            String originalFile = "original.txt";
            String encryptedFile = "encrypted.txt";
            String decryptedFile = "decrypted.txt";

            // 4️⃣ Make sure original.txt exists with some text inside it
            // (Create it manually in your project folder)

            // 5️⃣ Encrypt + store in DB
            controller.encryptFile(originalFile, encryptedFile);

            System.out.println("Encryption complete.");

            // 6️⃣ Retrieve latest record from database
            EncryptionDAO dao = new EncryptionDAO();
            List<EncryptionRecord> records = dao.getAllRecords();

            EncryptionRecord lastRecord = records.get(records.size() - 1);

            String storedKey = lastRecord.getEncryptionKey();
            String storedEncryptedPath = lastRecord.getEncryptedText();

            // 7️⃣ Decrypt using stored key
            controller.decryptFile(storedEncryptedPath, decryptedFile, storedKey);

            System.out.println("Decryption complete.");

            System.out.println("TEST SUCCESSFUL.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}