package saam.challenge.api.modules.metrics.dashboard;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saam.challenge.api.components.UserComponent;
import saam.challenge.api.models.User;
import saam.challenge.api.repositories.EmployeeRepository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class FindDashboardDataService {

    private final UserComponent userComponent;
    private final EmployeeRepository repository;

    public FindDashboardDataService(UserComponent userComponent, EmployeeRepository repository) {
        this.userComponent = userComponent;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public FindDashboardDataReturn handle() {
        User user = userComponent.findAuthenticated();
        FindDashboardDataReturn dto = new FindDashboardDataReturn();

        // Salários
        dto.setAverageSalary(repository.findAverageSalaryByUser(user));
        dto.setMinSalary(repository.findMinSalaryByUser(user));
        dto.setMaxSalary(repository.findMaxSalaryByUser(user));

        // Contagem ativos/inativos
        dto.setActiveCount(repository.countActiveEmployeesByUser(user));
        dto.setInactiveCount(repository.countInactiveEmployeesByUser(user));

        // Funcionário mais antigo e mais recente
        dto.setOldestEmployee(FindDashboardDataEmployeeReturn.fromEntity(repository.findOldestEmployeeByUser(user).stream().findFirst().orElse(null)));
        dto.setNewestEmployee(FindDashboardDataEmployeeReturn.fromEntity(repository.findNewestEmployeeByUser(user).stream().findFirst().orElse(null)));

        // Funcionários admitidos por mês no último ano
        OffsetDateTime startDate = OffsetDateTime.now(ZoneOffset.UTC).minusYears(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

        return dto;
    }
}
