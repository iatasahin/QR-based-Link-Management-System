package se360.shortlinker.swingclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDTO {
    @Getter
    @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;
}
