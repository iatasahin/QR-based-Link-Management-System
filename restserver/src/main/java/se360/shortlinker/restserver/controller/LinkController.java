package se360.shortlinker.restserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.service.LinkService;
import se360.shortlinker.restserver.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
public class LinkController {
    private final LinkService linkService;
    private final UserService userService;

    @GetMapping("/all")
    public List<Link> getAllLinks() {
        return linkService.getAllLinks();
    }

    @GetMapping("/add")
    public Link addLink(@RequestBody Link link) {
        return linkService.addLink(link);
    }
}
