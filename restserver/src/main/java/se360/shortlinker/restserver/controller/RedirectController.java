package se360.shortlinker.restserver.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.dao.LinksDAO;

@Controller
@RequestMapping("/qr")
@AllArgsConstructor
public class RedirectController {
    private LinksDAO linksDAO;
    @GetMapping("/{id}")
    public String redirect(@PathVariable String id) {
        try {
            linksDAO.incrementClickCount(Long.parseLong(id));
            Link link = linksDAO.getLink(Long.parseLong(id));
            return "redirect:" + link.getUrl();
        } catch (NumberFormatException e) {
            return "Invalid link id";
        } catch (NullPointerException e) {
            return "Link not found";
        }
    }
}
