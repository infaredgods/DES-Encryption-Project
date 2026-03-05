/**
 * Data Access Object (DAO) for encryption_records table.
 * Handles all database CRUD operations.
 */

package database;

import model.EncryptionRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncryptionDAO {
    /**
     * Inserts a new encryption record into the database.
     *
     * @param filename Original file name
     * @param originalText Original file content (optional)
     * @param encryptedText Path or encrypted content
     * @param key Base64 encoded DES key
     */

    public void insertRecord(String filename, String originalText,
                             String encryptedText, String key) {
        String sql = "INSERT INTO encryption_records(filename, original_text, encrypted_text, encryption_key) VALUES(?,?,?,?)";

    try (Connection conn = DatabaseManager.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, filename);
        pstmt.setString(2, originalText);
        pstmt.setString(3, encryptedText);
        pstmt.setString(4, key);

        pstmt.executeUpdate();

        System.out.println("Record inserted successfully.");

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    /**
     * Retrieves all encryption records from the database.
     *
     * @return List of EncryptionRecord objects
     */
    public List<EncryptionRecord> getAllRecords() {
        List<EncryptionRecord> records = new ArrayList<>();
    String sql = "SELECT * FROM encryption_records";

    try (Connection conn = DatabaseManager.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            EncryptionRecord record = new EncryptionRecord(
                    rs.getInt("id"),
                    rs.getString("filename"),
                    rs.getString("original_text"),
                    rs.getString("encrypted_text"),
                    rs.getString("encryption_key"),
                    rs.getString("date_created")
            );

            records.add(record);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return records;
    }

    /**
     * Deletes a record by ID.
     *
     * @param id Primary key of record
     */
    public void deleteRecord(int id) {

    String sql = "DELETE FROM encryption_records WHERE id = ?";

    try (Connection conn = DatabaseManager.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        pstmt.executeUpdate();

        System.out.println("Record deleted successfully.");

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}