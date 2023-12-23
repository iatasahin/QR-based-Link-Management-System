package se360.shortlinker.restserver.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se360.shortlinker.restserver.dao.LinksDAO;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LinkControllerTest {
    @Mock
    private LinksDAO linksDAO;
    @InjectMocks
    private LinkController linkController;
    @Test
    void testDeleteLink() {
        Long id = 1L;
        linkController.deleteLink(id);
        verify(linksDAO).deleteLink(id);
    }
}
