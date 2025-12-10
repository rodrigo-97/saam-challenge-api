package saam.challenge.api.modules.employees.createEmployee;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.components.UserComponent;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.EmployeeRepository;

@Service
public class CreateEmployeeService {

    private final UserComponent userComponent;
    private final EmployeeRepository repository;

    public CreateEmployeeService(UserComponent userComponent, EmployeeRepository repository) {
        this.userComponent = userComponent;
        this.repository = repository;
    }

    @Transactional
    public CreateEmployeeReturn handle(CreateEmployeeParams params) {
        // valida o usuário autenticado
        User user = userComponent.findAuthenticated();

        // cadastra novo empregado
        Employee createdEmployee = createEmployee(params, user);

        return CreateEmployeeReturn.fromEntity(createdEmployee);
    }

    private Employee createEmployee(CreateEmployeeParams params, User user) {
        Employee employee = new Employee();

        employee.setUser(user);
        employee.setName(params.getName());
        employee.setAdmissionDate(params.getAdmissionDate());
        employee.setSalary(params.getSalary());
        employee.setActive(params.getActive());

        return repository.save(employee);
    }
}
