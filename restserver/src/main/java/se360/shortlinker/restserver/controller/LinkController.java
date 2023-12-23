package se360.shortlinker.restserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se360.shortlinker.restserver.dao.UserDAO;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.dao.LinksDAO;
import se360.shortlinker.restserver.model.LinkDTO;
import se360.shortlinker.restserver.model.User;

@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
public class LinkController {
    private final LinksDAO linksDAO;
    private final UserDAO userDAO;
    @PostMapping("/add")
    public User addLink(@RequestBody LinkDTO linkDTO) {
        User user = userDAO.getUserById(linkDTO.getUser_id());
        Link link = Link.builder()
                .name(linkDTO.getName())
                .url(linkDTO.getUrl())
                .clicks(0L)
                .user(user)
                .build();
        linksDAO.saveLink(link);
        return userDAO.getUserById(linkDTO.getUser_id());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLink(@PathVariable Long id) {
        linksDAO.deleteLink(id);
    }
}
