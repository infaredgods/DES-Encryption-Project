// Isae Arreola
// File Encryption Application Project
// COMP 485
// 9 March 2026

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;

public class Interface extends JFrame {
    private JTextField filePathField;
    private JButton browseButton;
    private JButton encryptButton;
    private JButton decryptButton;

    public Interface() {
        setTitle("File Encryption Application");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
            // Call encryption logic here
            JOptionPane.showMessageDialog(this, "File encrypted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a file to encrypt.");
        }
    }

    private void decryptFile(ActionEvent e) {
        String filePath = filePathField.getText();
        if (!filePath.isEmpty()) {
            // Call decryption logic here
            JOptionPane.showMessageDialog(this, "File decrypted successfully!");
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