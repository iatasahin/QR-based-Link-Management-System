package se360.shortlinker.restserver.model;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter @Setter
public class LinkDTO {
    private Long id;
    private String name;
    private String url;
    private Long clicks;
    private Long user_id;
    private Instant createdAt;
}
