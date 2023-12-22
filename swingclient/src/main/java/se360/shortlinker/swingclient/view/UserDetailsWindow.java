package se360.shortlinker.swingclient.view;

import com.google.zxing.WriterException;
import lombok.Getter;
import se360.shortlinker.swingclient.Main;
import se360.shortlinker.swingclient.model.LinksTableModel;
import se360.shortlinker.swingclient.model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

public class UserDetailsWindow {
    private final JFrame frame;
    private final User user;
    @Getter
    private JPanel panel0;
    JTable table;

    public UserDetailsWindow(JFrame frame, User user) {
        this.frame = frame;
        this.user = user;

        initialize();
    }

    private void initialize() {
        panel0 = new JPanel();
        panel0.setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameLabel.setSize(150, 20);
        usernameLabel.setLocation(10, 20);
        panel0.add(usernameLabel);

        JLabel usernameField = new JLabel(user.getUsername());
        usernameField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameField.setSize(150, 20);
        usernameField.setLocation(120, 20);
        panel0.add(usernameField);

        JLabel createdLabel = new JLabel("Created: ");
        createdLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        createdLabel.setSize(150, 20);
        createdLabel.setLocation(10, 50);
        panel0.add(createdLabel);

        Instant instant = user.getCreatedAt();
        LocalDateTime createdAt = LocalDateTime.ofInstant(instant, java.time.ZoneId.systemDefault());
        String createdAtString = createdAt.format(Main.formatter);

        JLabel createdField = new JLabel(createdAtString);
        createdField.setFont(new Font("Arial", Font.PLAIN, 15));
        createdField.setSize(200, 20);
        createdField.setLocation(120, 50);
        panel0.add(createdField);

        JButton followLinkButton = new JButton("Follow Link");
        followLinkButton.setSize(150, 20);
        followLinkButton.setLocation(630, 5);
        followLinkButton.addActionListener(e -> followLink());
        panel0.add(followLinkButton);

        JButton downloadQRButton = new JButton("Download QR");
        downloadQRButton.setSize(150, 20);
        downloadQRButton.setLocation(630, 30);
        downloadQRButton.addActionListener(e -> downloadQR());
        panel0.add(downloadQRButton);

        JButton showQRButton = new JButton("Show QR");
        showQRButton.setSize(150, 20);
        showQRButton.setLocation(630, 55);
        showQRButton.addActionListener(e -> showQR());
        panel0.add(showQRButton);

        JButton deleteLinkButton = new JButton("Delete Link");
        deleteLinkButton.setSize(150, 20);
        deleteLinkButton.setLocation(630, 80);
        deleteLinkButton.addActionListener(e -> deleteLink());
        panel0.add(deleteLinkButton);

        table = new JTable(new LinksTableModel(user.getLinks()));
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setSize(800, 490);
        tablePanel.setLocation(0, 105);
        panel0.add(tablePanel);
    }

    private void followLink() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a link to follow.");
            return;
        }
        String shortUrl = (String) table.getValueAt(row, 3);
        try {
            Desktop.getDesktop().browse(java.net.URI.create(shortUrl));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to open link.");
        }
    }

    private void downloadQR() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a link to download QR for.");
            return;
        }
        String shortUrl = (String) table.getValueAt(row, 3);
        String linkName = ""+ table.getValueAt(row, 0);
        try {
            BufferedImage qrImage = Main.qrGenService.generateQrCodeImage(shortUrl);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(linkName + ".png"));
            int option = fileChooser.showSaveDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                ImageIO.write(qrImage, "PNG", file);
            }
        } catch (WriterException e) {
            System.out.println("UserDetailsWindow.downloadQR: Failed to generate QR.");
        } catch (IOException e) {
            System.out.println("UserDetailsWindow.downloadQR: Failed to save QR.");
        }
    }

    private void showQR() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a link to show QR for.");
            return;
        }
        String shortUrl = (String) table.getValueAt(row, 3);
        try {
            BufferedImage qrImage = Main.qrGenService.generateQrCodeImage(shortUrl);
            ImageIcon icon = new ImageIcon(qrImage);
            JOptionPane.showMessageDialog(frame, icon);
        } catch (WriterException e) {
            System.out.println("UserDetailsWindow.showQR: Failed to generate QR.");
            throw new RuntimeException(e);
        }
    }

    private void deleteLink() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a link to delete.");
            return;
        }

        // todo delete link request to server
    }
}
