/**
 * Handles database connection and initialization.
 * Creates the encryption_records table if it does not exist.
 * Uses SQLite as a lightweight embedded database.
 */

package database;

import java.sql.*;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:encryption.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
    * Creates the encryption_records table if it does not already exist.
    * Should be called once at application startup.
    */
    public static void initializeDatabase() {

        String sql = """
            CREATE TABLE IF NOT EXISTS encryption_records (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                filename TEXT NOT NULL,
                original_text TEXT,
                encrypted_text TEXT NOT NULL,
                encryption_key TEXT NOT NULL,
                date_created DATETIME DEFAULT CURRENT_TIMESTAMP
            );
        """;

        /**
        * Establishes a connection to the SQLite database.
        * @return Connection object
        */
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}