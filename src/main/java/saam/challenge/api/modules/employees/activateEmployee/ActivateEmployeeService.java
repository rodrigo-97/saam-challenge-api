package saam.challenge.api.modules.employees.activateEmployee;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.components.UserComponent;
import saam.challenge.api.exceptions.EmployeeNotFoundException;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class ActivateEmployeeService {

    private final UserComponent userComponent;
    private final EmployeeRepository repository;

    public ActivateEmployeeService(UserComponent userComponent, EmployeeRepository repository) {
        this.userComponent = userComponent;
        this.repository = repository;
    }

    @Transactional
    public void handle(Long employeeId) {
        // valida usuário logado
        User user = userComponent.findAuthenticated();

        // busca funcionário que será ativado novamente
        Employee employeeToActivate = findEmployeeToActivate(employeeId, user);

        // ativa novamente o funcionário
        activateEmployee(employeeToActivate);
    }

    private void activateEmployee(Employee employee) {
        employee.setActive(true);

        repository.save(employee);
    }

    private Employee findEmployeeToActivate(Long employeeId, User user) {
        Optional<Employee> employee = repository.findByIdAndUserAndActiveFalse(employeeId, user);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get();
    }
}
