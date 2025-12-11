package saam.challenge.api.modules.auth.signUp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.exceptions.UserAlreadyExistsException;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.UserRepository;

import java.util.Optional;

@Service
public class SignUpService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.passwordEncoder = encoder;
    }

    @Transactional
    public void handle(SignUpParams params) {
        // valida se já existe um usuário com o username ou email que foi passado na req
        validateUser(params);

        // cria novo usuário
        createUser(params);
    }

    private void createUser(SignUpParams params) {
        String encodedPassword = passwordEncoder.encode(params.getPassword());

        User newUser = new User();
        newUser.setUsername(params.getUsername());
        newUser.setEmail(params.getEmail());
        newUser.setFirstName(params.getFirstName());
        newUser.setLastName(params.getLastName());
        newUser.setPasswordHash(encodedPassword);

        repository.save(newUser);
    }

    private void validateUser(SignUpParams params) {
        Optional<User> isUserExists = repository.findByUsernameOrEmail(params.getUsername(), params.getEmail());

        if (isUserExists.isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }
}
