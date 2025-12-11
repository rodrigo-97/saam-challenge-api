package saam.challenge.api.modules.metrics.dashboard;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FindDashboardDataReturn {
    private BigDecimal averageSalary;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private Long activeCount;
    private Long inactiveCount;
    private FindDashboardDataEmployeeReturn oldestEmployee;
    private FindDashboardDataEmployeeReturn newestEmployee;
}
