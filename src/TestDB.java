import database.DatabaseManager;
import database.EncryptionDAO;
import model.EncryptionRecord;

import java.util.List;

public class TestDB {

    public static void main(String[] args) {

        // 1️⃣ Initialize database (creates table if not exists)
        DatabaseManager.initializeDatabase();

        // 2️⃣ Create DAO object
        EncryptionDAO dao = new EncryptionDAO();

        // 3️⃣ Insert a test record
        dao.insertRecord(
                "testfile.txt",
                "Hello World",
                "XJ82KS92JSD92J",
                "MySecretKey"
        );

        // 4️⃣ Retrieve all records
        List<EncryptionRecord> records = dao.getAllRecords();

        // 5️⃣ Print records to console
        for (EncryptionRecord record : records) {
            System.out.println("ID: " + record.getId());
            System.out.println("Filename: " + record.getFilename());
            System.out.println("Encrypted Text: " + record.getEncryptedText());
            System.out.println("Key: " + record.getEncryptionKey());
            System.out.println("Date: " + record.getDateCreated());
            System.out.println("----------------------------");
        }
    }
}