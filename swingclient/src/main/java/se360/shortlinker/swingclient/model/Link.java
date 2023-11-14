package se360.shortlinker.swingclient.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder @ToString @EqualsAndHashCode
public class Link {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String url;

    @Getter @Setter
    private String shortUrl;

    @Getter @Setter
    private Long clicks;

    @Getter @Setter
    @JsonBackReference
    @ToString.Exclude
    private User user;

    @Getter @Setter
    private Instant createdAt;

    @Getter @Setter
    private Instant updatedAt;

    @Getter @Setter
    private Instant deletedAt;

    @Getter @Setter
    private Boolean deleted;
}
