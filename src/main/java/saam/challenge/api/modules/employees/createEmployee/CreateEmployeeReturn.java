package saam.challenge.api.modules.employees.createEmployee;

import lombok.Getter;
import lombok.Setter;
import saam.challenge.api.models.Employee;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateEmployeeReturn {

    private Long id;
    private String name;
    private OffsetDateTime admissionDate;
    private BigDecimal salary;
    private Boolean active;

    public static CreateEmployeeReturn fromEntity(Employee employee) {
        CreateEmployeeReturn data = new CreateEmployeeReturn();

        data.setId(employee.getId());
        data.setName(employee.getName());
        data.setAdmissionDate(employee.getAdmissionDate());
        data.setSalary(employee.getSalary());
        data.setActive(employee.getActive());

        return data;
    }
}
