package se360.shortlinker.restserver.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCredentialsDTO {
    @Getter
    @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;
}
