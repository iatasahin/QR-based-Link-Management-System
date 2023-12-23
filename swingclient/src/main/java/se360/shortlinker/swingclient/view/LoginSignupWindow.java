package se360.shortlinker.swingclient.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import lombok.Getter;
import se360.shortlinker.swingclient.Main;
import se360.shortlinker.swingclient.model.User;
import se360.shortlinker.swingclient.model.UserCredentialsDTO;
import se360.shortlinker.swingclient.service.LoginSignupService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginSignupWindow {
    private final JFrame frame;
    @Getter
    private JPanel panel0;
    private static final LoginSignupService loginSignupService = Main.loginSignupService;
    private JButton loginButton;
    private JButton signUpButton;

    public LoginSignupWindow(JFrame frame) {
        this.frame = frame;
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

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameField.setSize(190, 20);
        usernameField.setLocation(410, 150);
        panel0.add(usernameField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setSize(190, 20);
        passwordLabel.setLocation(200, 200);
        panel0.add(passwordLabel);


        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setSize(190, 20);
        passwordField.setLocation(410, 200);
        panel0.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setSize(400, 20);
        loginButton.setLocation(200, 250);
        panel0.add(loginButton);

        JLabel emailLabel = new JLabel("E-mail: ");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setSize(190, 20);
        emailLabel.setLocation(200, 300);
        panel0.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));
        emailField.setSize(190, 20);
        emailField.setLocation(410, 300);
        panel0.add(emailField);

        signUpButton = new JButton("Sign Up");
        signUpButton.setSize(400, 20);
        signUpButton.setLocation(200, 350);
        panel0.add(signUpButton);

        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    loginButton.doClick();
            }
        });

        signUpButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    signUpButton.doClick();
            }
        });

        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO(username, password, email);

            try {
                User user = loginSignupService.login(userCredentialsDTO);
                frame.setContentPane(new UserDetailsWindow(frame, user).getPanel0());
                frame.setVisible(true);
            }
            catch (NotFoundException exception ){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "404, Not Found (username does not exist)!");
            }
            catch (ForbiddenException exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "403, Forbidden (passwords don't match)!");
            }
            catch (JsonProcessingException exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "JsonProcessingException (IDK)!");
            }
            catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Unexpected exception (IDK)!");
            }
        });

        signUpButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO(username, password, email);

            try {
                User user = loginSignupService.signup(userCredentialsDTO);
                frame.setContentPane(new UserDetailsWindow(frame, user).getPanel0());
                frame.setVisible(true);
            }
            catch (ForbiddenException exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "403, Forbidden (username already exists)!");
            }
            catch (JsonProcessingException exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "JsonProcessingException (IDK)!");
            }
            catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Unexpected exception (IDK)!");
            }
        });
    }
}
