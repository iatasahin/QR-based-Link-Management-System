package se360.shortlinker.swingclient.model;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder @ToString @EqualsAndHashCode
public class LinkDTO {
    private Long id;
    private String name;
    private String url;
    private Long clicks;
    private Long user_id;
    private Instant createdAt;
}
