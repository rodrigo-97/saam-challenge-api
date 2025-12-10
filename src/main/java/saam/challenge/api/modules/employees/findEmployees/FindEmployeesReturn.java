package saam.challenge.api.modules.employees.findEmployees;

import lombok.Getter;
import lombok.Setter;
import saam.challenge.api.models.Employee;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class FindEmployeesReturn {

    private Long id;
    private String name;
    private OffsetDateTime admissionDate;
    private BigDecimal salary;
    private Boolean active;

    public static FindEmployeesReturn fromEntity(Employee employee) {
        FindEmployeesReturn data = new FindEmployeesReturn();

        data.setId(employee.getId());
        data.setName(employee.getName());
        data.setAdmissionDate(employee.getAdmissionDate());
        data.setSalary(employee.getSalary());
        data.setActive(employee.getActive());

        return data;
    }
}
