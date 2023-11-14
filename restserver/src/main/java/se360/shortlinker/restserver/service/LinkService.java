package se360.shortlinker.restserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.repository.LinkRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;

    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    public Link addLink(Link link) {
        return linkRepository.save(link);
    }

    public List<Link> getLinksByUserId(Long id) {
        return linkRepository.findByUser_Id(id);
    }
}