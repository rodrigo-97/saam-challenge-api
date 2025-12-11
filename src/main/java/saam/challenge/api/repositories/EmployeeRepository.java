package saam.challenge.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByIdAndUser(Long id, User user);

    Optional<Employee> findByIdAndUserAndActiveTrue(Long id, User user);

    Optional<Employee> findByIdAndUserAndActiveFalse(Long id, User user);

    Page<Employee> findByUser(User user, Pageable pageable);

    // Salário médio por usuário
    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.user = :user")
    BigDecimal findAverageSalaryByUser(User user);

    // Salário mínimo e máximo por usuário
    @Query("SELECT MIN(e.salary) FROM Employee e WHERE e.user = :user")
    BigDecimal findMinSalaryByUser(User user);

    @Query("SELECT MAX(e.salary) FROM Employee e WHERE e.user = :user")
    BigDecimal findMaxSalaryByUser(User user);

    // Número de funcionários ativos/inativos por usuário
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.user = :user AND e.active = true")
    Long countActiveEmployeesByUser(User user);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.user = :user AND e.active = false")
    Long countInactiveEmployeesByUser(User user);

    // Funcionário mais antigo
    @Query("SELECT e FROM Employee e WHERE e.user = :user ORDER BY e.admissionDate ASC")
    List<Employee> findOldestEmployeeByUser(User user);

    // Funcionário mais recente
    @Query("SELECT e FROM Employee e WHERE e.user = :user ORDER BY e.admissionDate DESC")
    List<Employee> findNewestEmployeeByUser(User user);
}
