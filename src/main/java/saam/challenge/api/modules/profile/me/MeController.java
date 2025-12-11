package saam.challenge.api.modules.profile.me;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Profile")
@RestController
@RequestMapping("/profile")
public class MeController {

    @GetMapping("/me")
    @Operation(summary = "Retorna os dados do usuário logado")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaim("user");
    }
}
