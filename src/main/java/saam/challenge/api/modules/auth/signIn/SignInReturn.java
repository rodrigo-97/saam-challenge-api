package saam.challenge.api.modules.auth.signIn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInReturn {

    private String accessToken;
    private Long expiresIn;
}
