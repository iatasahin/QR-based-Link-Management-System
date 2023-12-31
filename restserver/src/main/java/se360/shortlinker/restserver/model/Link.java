package se360.shortlinker.restserver.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Link {
    private Long id;
    private String name;
    private String url;
    private Long clicks;
    @JsonBackReference
    private User user;
    private Instant createdAt;
}
