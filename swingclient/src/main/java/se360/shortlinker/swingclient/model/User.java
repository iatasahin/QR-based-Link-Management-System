package se360.shortlinker.swingclient.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString @EqualsAndHashCode
@Getter @Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    @JsonManagedReference
    private List<Link> links;
    private Instant createdAt;
}
