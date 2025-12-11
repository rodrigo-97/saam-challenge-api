package saam.challenge.api.modules.employees.updateEmployee;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.components.UserComponent;
import saam.challenge.api.exceptions.EmployeeNotFoundException;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.EmployeeRepository;

import java.util.Optional;

@Service
public class UpdateEmployeeService {

    private final UserComponent userComponent;
    private final EmployeeRepository repository;

    public UpdateEmployeeService(UserComponent userComponent, EmployeeRepository repository) {
        this.userComponent = userComponent;
        this.repository = repository;
    }

    @Transactional
    public void handle(Long employeeId, UpdateEmployeeParams params) {
        // valida usuário logado
        User user = userComponent.findAuthenticated();

        // busca o funcionário que terá os dados atualizado
        Employee employee = findEmployeeToUpdate(employeeId, user);

        // atualiza os dados do usuário
        updateEmployeeData(employee, params);
    }

    private void updateEmployeeData(Employee employee, UpdateEmployeeParams params) {
        employee.setName(params.getName());
        employee.setAdmissionDate(params.getAdmissionDate());
        employee.setSalary(params.getSalary());
        employee.setActive(params.getActive());

        repository.save(employee);
    }

    private Employee findEmployeeToUpdate(Long employeeId, User user) {
        Optional<Employee> employee = repository.findByIdAndUser(employeeId, user);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException();
        }

        return employee.get();
    }
}
