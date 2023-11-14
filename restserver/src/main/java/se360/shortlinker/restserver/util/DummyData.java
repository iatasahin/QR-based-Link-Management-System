package se360.shortlinker.restserver.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se360.shortlinker.restserver.model.Link;
import se360.shortlinker.restserver.model.User;
import se360.shortlinker.restserver.service.LinkService;
import se360.shortlinker.restserver.service.UserService;

import java.time.Instant;

@Configuration
public class DummyData {

    @Bean
    public CommandLineRunner createDemoData(UserService userService, LinkService linkService) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                User user = User.builder()
                        .username("user" + i)
                        .password("password" + i)
                        .email("user" + i + "@qwe" + i + ".com")
                        .deleted(false)
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .build();
                userService.addUser(user);
            }

            for (int i = 0; i < 100; i++) {
                Link link = Link.builder()
                        .url("https://www.google.com/" + i)
                        .shortUrl("https://www.goo.gl/" + i)
                        .clicks((long) i * i)
                        .deleted(false)
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .user(userService.getUserById((long) i % 10 +1))
                        .build();
                linkService.addLink(link);
            }
        };
    }
}
