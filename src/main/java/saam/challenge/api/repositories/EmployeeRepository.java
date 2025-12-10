package saam.challenge.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByIdAndUserAndActiveTrue(Long id, User user);

    Page<Employee> findByUserAndActiveTrue(User user, Pageable pageable);
}
