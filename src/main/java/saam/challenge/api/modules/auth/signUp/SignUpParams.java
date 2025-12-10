package saam.challenge.api.modules.auth.signUp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpParams {

    @NotEmpty(message = "O nome de usuário não pode ser vazio.")
    @Size(min = 2, max = 255, message = "O nome de usuário deve ter entre {min} e {max} caracteres.")
    @Schema(description = "Nome de usuário", example = "jhon.doe")
    private String username;

    @NotEmpty(message = "O e-mail não pode ser vazio.")
    @Email(message = "O formato do e-mail é inválido.")
    @Size(max = 255, message = "O e-mail deve ter no máximo {max} caracteres.")
    @Schema(description = "E-mail do usuário", example = "jhon.doe@saamauditoria.com.br")
    private String email;

    @NotEmpty(message = "O primeiro nome não pode ser vazio.")
    @Size(max = 255, message = "O primeiro nome deve ter no máximo {max} caracteres.")
    @Schema(description = "Primeiro nome do usuário", example = "Jhon")
    private String firstName;

    @NotEmpty(message = "O sobrenome não pode ser vazio.")
    @Size(max = 255, message = "O sobrenome deve ter no máximo {max} caracteres.")
    @Schema(description = "Sobrenome do usuário", example = "Doe")
    private String lastName;

    @NotEmpty(message = "A senha não pode ser vazia.")
    @Size(min = 8, max = 32, message = "A senha deve ter entre {min} e {max} caracteres.")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
            message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial."
    )
    @Schema(description = "Senha do usuário", example = "?20Buk*YVo+9oD.6")
    private String password;
}
