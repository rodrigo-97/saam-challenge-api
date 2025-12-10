package saam.challenge.api.components;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.exceptions.UserNotFoundException;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserComponent {

    private final UserRepository repository;

    public UserComponent(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User findAuthenticated() {
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());

        Optional<User> user = repository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }
}
