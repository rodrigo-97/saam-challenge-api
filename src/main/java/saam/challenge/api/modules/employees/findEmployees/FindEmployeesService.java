package saam.challenge.api.modules.employees.findEmployees;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.components.UserComponent;
import saam.challenge.api.models.Employee;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.EmployeeRepository;
import saam.challenge.api.shared.returnData.Paginated;

import java.util.List;

@Service
public class FindEmployeesService {

    private final UserComponent userComponent;
    private final EmployeeRepository repository;

    public FindEmployeesService(UserComponent userComponent, EmployeeRepository repository) {
        this.userComponent = userComponent;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Paginated<FindEmployeesReturn> handle(FindEmployeesParams params) {
        User user = userComponent.findAuthenticated();
        Pageable pageable = PageRequest.of(params.getPage() - 1, params.getSize(), Sort.by("id").descending());
        Page<Employee> page = repository.findByUserAndActiveTrue(user, pageable);

        List<FindEmployeesReturn> employees = page
                .getContent()
                .stream()
                .map(FindEmployeesReturn::fromEntity)
                .toList();

        return Paginated.of(employees, page.getNumber() + 1, page.getSize(), page.getTotalElements());
    }
}
