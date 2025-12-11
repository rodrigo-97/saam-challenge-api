package saam.challenge.api.modules.auth.signIn;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInParams {

    @NotEmpty(message = "O nome de usuário é obrigatório.")
    @Size(max = 255, message = "O campo de usuário não pode exceder {max} caracteres.")
    @Schema(description = "Nome de usuário", example = "admin")
    private String username;

    @NotEmpty(message = "A senha é obrigatória.")
    @Size(min = 4, max = 32, message = "A senha deve ter entre {min} e {max} caracteres.")
    @Schema(description = "Senha do usuário", example = "adminadmin")
    private String password;
}
