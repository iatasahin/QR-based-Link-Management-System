package se360.shortlinker.swingclient.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder @ToString @EqualsAndHashCode
@Getter @Setter
public class Link {
    private Long id;
    private String name;
    private String url;
    private Long clicks;
    @JsonBackReference
    @ToString.Exclude
    private User user;
    private Instant createdAt;
}
