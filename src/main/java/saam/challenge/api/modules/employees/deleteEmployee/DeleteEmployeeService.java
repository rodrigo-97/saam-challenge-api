package saam.challenge.api.modules.employees.deleteEmployee;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.components.UserComponent;
import saam.challenge.api.exceptions.EmployeeNotFoundException;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class DeleteEmployeeService {

    private final UserComponent userComponent;
    private final EmployeeRepository repository;

    public DeleteEmployeeService(EmployeeRepository repository, UserComponent userComponent) {
        this.repository = repository;
        this.userComponent = userComponent;
    }

    @Transactional
    public void handle(Long employeedId) {
        // valida usuário logado
        User user = userComponent.findAuthenticated();

        // busca functionário que será deletado
        Employee employee = findEmployeeToDelete(employeedId, user);

        // executa a deleção
        deleteEmployee(employee);
    }

    private Employee findEmployeeToDelete(Long employeeId, User user) {
        Optional<Employee> employee = repository.findByIdAndUser(employeeId, user);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get();
    }

    private void deleteEmployee(Employee employee) {
        repository.delete(employee);
    }
}
