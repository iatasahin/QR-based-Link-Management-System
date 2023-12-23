package se360.shortlinker.swingclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import se360.shortlinker.swingclient.Main;
import se360.shortlinker.swingclient.model.LinkDTO;
import se360.shortlinker.swingclient.model.User;

public class LinkService {
    public static final String API_URL = Main.API_URL;
    public static final Client client = Main.client;

    public User createLink(LinkDTO linkDTO) throws JsonProcessingException {
        WebTarget webTarget = client.target((API_URL + "/link/add"));
        String response = webTarget.request()
                .post(Entity.entity(
                        linkDTO, MediaType.APPLICATION_JSON), String.class);
        return Main.objectMapper.readValue(response, new TypeReference<User>() {});
    }
    public void deleteLink(Long id) {
        WebTarget webTarget = client.target((API_URL + "/link/delete/" + id));
        webTarget.request().delete();
    }
}
