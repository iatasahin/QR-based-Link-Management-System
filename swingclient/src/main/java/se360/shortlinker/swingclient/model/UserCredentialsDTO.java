package se360.shortlinker.swingclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserCredentialsDTO {
    private String username;
    private String password;
    private String email;
}
