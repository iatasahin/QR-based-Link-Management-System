package se360.shortlinker.swingclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.*;
import se360.shortlinker.swingclient.service.LoginSignupService;
import se360.shortlinker.swingclient.view.LoginSignupWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static String API_URL = "http://localhost:8080/api";
    public static Dimension preferredDimension = new Dimension(800, 600);
    public static final Client client = ClientBuilder.newClient();
    public static final LoginSignupService loginSignupService = new LoginSignupService();
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        objectMapper.findAndRegisterModules();

        JFrame frame = new JFrame("Link Shortener App - SE360");
        frame.setContentPane(new LoginSignupWindow(frame).getPanel0());
        frame.setPreferredSize(preferredDimension);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
