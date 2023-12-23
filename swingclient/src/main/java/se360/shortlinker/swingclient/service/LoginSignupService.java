package se360.shortlinker.swingclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import se360.shortlinker.swingclient.Main;
import se360.shortlinker.swingclient.model.User;
import se360.shortlinker.swingclient.model.UserCredentialsDTO;

public class LoginSignupService {
    public static final String API_URL = Main.API_URL;
    public static final Client client = Main.client;
    public User login(UserCredentialsDTO uC) throws JsonProcessingException {

        WebTarget webTarget = client.target((API_URL + "/user/details"));
        String response = webTarget.request()
                .post(Entity.entity(
                        uC, MediaType.APPLICATION_JSON_TYPE), String.class);

        return Main.objectMapper.readValue(response, new TypeReference<User>() {});
    }

    public User signup(UserCredentialsDTO user) throws JsonProcessingException {
        WebTarget webTarget = client.target((API_URL + "/user/add"));
        String response = webTarget.request()
                .post(Entity.entity(
                        user, MediaType.APPLICATION_JSON_TYPE), String.class);

        return Main.objectMapper.readValue(response, new TypeReference<User>() {});
    }
}
