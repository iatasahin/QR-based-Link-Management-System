package se360.shortlinker.restserver.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se360.shortlinker.restserver.dao.LinksDAO;
import se360.shortlinker.restserver.model.Link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RedirectControllerTest {
    @Mock
    private LinksDAO linksDAO;
    @InjectMocks
    private RedirectController redirectController;

    @Test
    void testRedirectValidLink() {
        Long id = 1L;
        Link link = Link.builder()
                .id(id)
                .name("test")
                .url("https://www.google.com")
                .clicks(0L)
                .build();
        when(linksDAO.getLink(id)).thenReturn(link);
        String result = redirectController.redirect(id.toString());
        verify(linksDAO).incrementClickCount(id);
        assertEquals("redirect:https://www.google.com", result);
    }

    @Test
    void testRedirectInvalidLink() {
        String invalidId = "123invalid";
        String result = redirectController.redirect(invalidId);
        verifyNoInteractions(linksDAO);
        assertEquals("Invalid link id", result);
    }

    @Test
    void testRedirectLinkNotFound() {
        Long id = 999L;
        when(linksDAO.getLink(id)).thenReturn(null);
        String result = redirectController.redirect(id.toString());
        assertEquals("Link not found", result);
    }
}
