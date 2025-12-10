package saam.challenge.api.modules.auth.signIn;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth")
@RequestMapping("/auth")
public class SignInController {

    private final SignInService service;

    public SignInController(SignInService service) {
        this.service = service;
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Realiza autenticação do usuário")
    public ResponseEntity<SignInReturn> handle(@RequestBody @Valid SignInParams params) {
        SignInReturn returnData = service.handle(params);
        return ResponseEntity.status(HttpStatus.OK).body(returnData);
    }
}
