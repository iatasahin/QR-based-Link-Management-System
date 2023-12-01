package se360.shortlinker.swingclient.view;

import lombok.Getter;
import se360.shortlinker.swingclient.model.User;

import javax.swing.*;
import java.awt.*;

public class UserDetailsWindow {
    private final JFrame frame;
    private final User user;
    @Getter
    private JPanel panel0;

    public UserDetailsWindow(JFrame frame, User user) {
        this.frame = frame;
        this.user = user;

        initialize();
    }

    private void initialize() {
        panel0 = new JPanel();
        panel0.setLayout(null);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setSize(190, 20);
        usernameLabel.setLocation(200, 150);
        panel0.add(usernameLabel);

        JLabel usernameField = new JLabel(user.getUsername());
        usernameField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameField.setSize(190, 20);
        usernameField.setLocation(410, 150);
        panel0.add(usernameField);

        JLabel createdLabel = new JLabel("Created: ");
        createdLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        createdLabel.setSize(190, 20);
        createdLabel.setLocation(200, 200);
        panel0.add(createdLabel);

        JLabel createdField = new JLabel(user.getCreatedAt().toString());
        createdField.setFont(new Font("Arial", Font.PLAIN, 15));
        createdField.setSize(190, 20);
        createdField.setLocation(410, 200);
        panel0.add(createdField);
    }
}
