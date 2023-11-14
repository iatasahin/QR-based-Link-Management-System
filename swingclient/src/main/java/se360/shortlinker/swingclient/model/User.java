package se360.shortlinker.swingclient.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString @EqualsAndHashCode
public class User {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;

    @Getter @Setter
    @JsonManagedReference
    private List<Link> links;

    @Getter @Setter
    private Instant createdAt;

    @Getter @Setter
    private Instant updatedAt;

    @Getter @Setter
    private Instant deletedAt;

    @Getter @Setter
    private Boolean deleted;
}
