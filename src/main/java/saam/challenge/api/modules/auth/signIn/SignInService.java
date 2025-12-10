package saam.challenge.api.modules.auth.signIn;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.exceptions.InvalidCredentialsException;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.UserRepository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SignInService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    private final Instant NOW = Instant.now();
    private final Instant TOKEN_EXPIRATION = Instant.now().plusSeconds(60 * 60 * 24); // 1 dia em segundos

    public SignInService(UserRepository repository, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    @Transactional
    public SignInReturn handle(SignInParams params) {
        // valida as credenciais do usuário
        User user = validateUser(params);

        // cria o token JWT
        String jwtToken = createJwtToken(user);

        return new SignInReturn(jwtToken, TOKEN_EXPIRATION.getEpochSecond());
    }

    private String createJwtToken(User user) {
        Map<String, String> userData = new HashMap<>();

        userData.put("id", user.getId().toString());
        userData.put("username", user.getUsername());
        userData.put("email", user.getEmail());
        userData.put("firstName", user.getFirstName());
        userData.put("lastName", user.getLastName());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("https://saamauditoria.challenge.com")
                .subject(user.getId().toString())
                .expiresAt(TOKEN_EXPIRATION)
                .issuedAt(NOW)
                .claim("user", userData)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private User validateUser(SignInParams params) {
        Optional<User> user = repository.findByUsername(params.getUsername());

        if (user.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        boolean isPasswordMatch = passwordEncoder.matches(params.getPassword(), user.get().getPasswordHash());
        if (!isPasswordMatch) {
            throw new InvalidCredentialsException();
        }

        return user.get();
    }
}
