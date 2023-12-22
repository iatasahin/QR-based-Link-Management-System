package se360.shortlinker.restserver.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.service.LinkService;

@RestController
@RequestMapping("/sl")
@AllArgsConstructor
public class RedirectController {
    private LinkService linkService;
    @GetMapping("/{id}")
    public String redirect(@PathVariable String id) {
        try {
            Link link = linkService.incrementAndGetLink(id);
            return "redirect:/" + link.getUrl();
        } catch (NumberFormatException e) {
            return "Invalid link id format";
        } catch (NullPointerException e) {
            return "Link not found";
        }
    }
}
