package saam.challenge.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saam.challenge.api.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
}
