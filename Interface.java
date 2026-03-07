// Isae Arreola
// File Encryption Application Project
// COMP 485
// 9 March 2026

import java.awt.event.ActionEvent;
import java.io.File;
import javax.crypto.SecretKey;
import javax.swing.*;

public class Interface extends JFrame {
    private JTextField filePathField;
    private JButton browseButton;
    private JButton encryptButton;
    private JButton decryptButton;
    
    // Maintain a reference to the key for the session
    private SecretKey sessionKey;

    public Interface() {
        setTitle("File Encryption Application");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize the key using the logic from DES_Encryption
        try {
            sessionKey = DES_Encryption.generateKey();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error generating encryption key: " + e.getMessage());
        }
        
        initComponents();
    }

    private void initComponents() {
        filePathField = new JTextField(20);
        browseButton = new JButton("Browse");
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        JPanel panel = new JPanel();
        panel.add(filePathField);
        panel.add(browseButton);
        panel.add(encryptButton);
        panel.add(decryptButton);

        add(panel);

        browseButton.addActionListener(this::browseFile);
        encryptButton.addActionListener(this::encryptFile);
        decryptButton.addActionListener(this::decryptFile);
    }

    private void browseFile(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void encryptFile(ActionEvent e) {
        String filePath = filePathField.getText();
        if (!filePath.isEmpty()) {
            try {
                // Logic: Save encrypted version as [original_filename].enc
                String outputPath = filePath + ".enc";
                DES_Encryption.encrypt(filePath, outputPath, sessionKey);
                
                JOptionPane.showMessageDialog(this, "File encrypted successfully!\nSaved to: " + outputPath);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Encryption failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a file to encrypt.");
        }
    }

    private void decryptFile(ActionEvent e) {
        String filePath = filePathField.getText();
        if (!filePath.isEmpty()) {
            try {
                // Logic: Save decrypted version as [original_filename].dec
                String outputPath = filePath.replace(".enc", "") + "_decrypted.txt";
                DES_Encryption.decrypt(filePath, outputPath, sessionKey);
                
                JOptionPane.showMessageDialog(this, "File decrypted successfully!\nSaved to: " + outputPath);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Decryption failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a file to decrypt.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface appInterface = new Interface();
            appInterface.setVisible(true);
        });
    }
}