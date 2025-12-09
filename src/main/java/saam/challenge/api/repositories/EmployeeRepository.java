package saam.challenge.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saam.challenge.api.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
