package se360.shortlinker.swingclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.*;
import se360.shortlinker.swingclient.service.LinkService;
import se360.shortlinker.swingclient.service.LoginSignupService;
import se360.shortlinker.swingclient.service.QrGenService;
import se360.shortlinker.swingclient.view.LoginSignupWindow;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static String API_URL = "http://localhost:8080/api";
    public static String QR_URL = "http://localhost:8080/qr";
    public static Dimension preferredDimension = new Dimension(800, 600);
    public static final Client client = ClientBuilder.newClient();
    public static final LoginSignupService loginSignupService = new LoginSignupService();
    public static final LinkService linkService = new LinkService();
    public static final QrGenService qrGenService = new QrGenService();
    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
