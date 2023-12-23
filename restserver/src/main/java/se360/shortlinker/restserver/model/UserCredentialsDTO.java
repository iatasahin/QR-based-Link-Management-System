package se360.shortlinker.restserver.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
public class UserCredentialsDTO {
    private String username;
    private String password;
    private String email;
}
