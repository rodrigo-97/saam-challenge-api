package saam.challenge.api.modules.employees.findEmployee;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.components.UserComponent;
import saam.challenge.api.exceptions.EmployeeNotFoundException;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class FindEmployeeService {

    private final UserComponent userComponent;
    private final EmployeeRepository repository;

    public FindEmployeeService(UserComponent userComponent, EmployeeRepository repository) {
        this.userComponent = userComponent;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public FindEmployeeReturn handle(Long employeeId) {
        // valida usuário logado
        User user = userComponent.findAuthenticated();

        // busca os dados do empregado
        Employee employee = findEmployee(employeeId, user);

        return FindEmployeeReturn.fromEntity(employee);
    }

    private Employee findEmployee(Long employeeId, User user) {
        Optional<Employee> employee = repository.findByIdAndUserAndActiveTrue(employeeId, user);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get();
    }
}
