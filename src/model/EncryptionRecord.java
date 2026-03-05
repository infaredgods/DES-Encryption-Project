package model;

public class EncryptionRecord {

    private int id;
    private String filename;
    private String originalText;
    private String encryptedText;
    private String encryptionKey;
    private String dateCreated;

    public EncryptionRecord(int id, String filename, String originalText,
                            String encryptedText, String encryptionKey,
                            String dateCreated) {

        this.id = id;
        this.filename = filename;
        this.originalText = originalText;
        this.encryptedText = encryptedText;
        this.encryptionKey = encryptionKey;
        this.dateCreated = dateCreated;
    }

    public int getId() { return id; }
    public String getFilename() { return filename; }
    public String getOriginalText() { return originalText; }
    public String getEncryptedText() { return encryptedText; }
    public String getEncryptionKey() { return encryptionKey; }
    public String getDateCreated() { return dateCreated; }
}