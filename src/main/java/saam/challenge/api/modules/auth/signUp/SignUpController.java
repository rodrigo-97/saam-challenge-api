package saam.challenge.api.modules.auth.signUp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth")
@RequestMapping("/auth")
public class SignUpController {

    private final SignUpService service;

    public SignUpController(SignUpService service) {
        this.service = service;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Cadastra um novo usuário")
    public ResponseEntity<Void> handle(@RequestBody @Valid SignUpParams params) {
        service.handle(params);
        return ResponseEntity.noContent().build();
    }
}
