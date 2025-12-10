package saam.challenge.api.modules.employees.findEmployee;

import lombok.Getter;
import lombok.Setter;
import saam.challenge.api.models.Employee;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class FindEmployeeReturn {

    private Long id;
    private String name;
    private OffsetDateTime admissionDate;
    private BigDecimal salary;
    private Boolean active;

    public static FindEmployeeReturn fromEntity(Employee employee) {
        FindEmployeeReturn data = new FindEmployeeReturn();

        data.setId(employee.getId());
        data.setName(employee.getName());
        data.setAdmissionDate(employee.getAdmissionDate());
        data.setSalary(employee.getSalary());
        data.setActive(employee.getActive());

        return data;
    }
}
