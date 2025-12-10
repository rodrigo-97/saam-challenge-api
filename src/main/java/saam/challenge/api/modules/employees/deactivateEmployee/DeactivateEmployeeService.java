package saam.challenge.api.modules.employees.deactivateEmployee;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.components.UserComponent;
import saam.challenge.api.exceptions.EmployeeNotFoundException;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class DeactivateEmployeeService {

    private final UserComponent userComponent;
    private final EmployeeRepository repository;

    public DeactivateEmployeeService(UserComponent userComponent, EmployeeRepository repository) {
        this.userComponent = userComponent;
        this.repository = repository;
    }

    @Transactional
    public void handle(Long employeeId) {
        // valida usuário logado
        User user = userComponent.findAuthenticated();

        // busca empregado que será desativado
        Employee employeeToDeactivate = findEmployeeToDeactivate(employeeId, user);

        // desativa o empregado
        deactivateEmployee(employeeToDeactivate);
    }

    private void deactivateEmployee(Employee employee) {
        employee.setActive(false);

        repository.save(employee);
    }

    private Employee findEmployeeToDeactivate(Long employeeId, User user) {
        Optional<Employee> employee = repository.findByIdAndUserAndActiveTrue(employeeId, user);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get();
    }
}
