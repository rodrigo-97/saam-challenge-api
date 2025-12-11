package saam.challenge.api.modules.employees.activateEmployee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employees")
@RestController
@RequestMapping("/employees")
public class ActivateEmployeeController {

    private final ActivateEmployeeService service;

    public ActivateEmployeeController(ActivateEmployeeService service) {
        this.service = service;
    }

    @PatchMapping("/{employeeId}/activate")
    @Operation(summary = "Ativa um funcionário")
    public ResponseEntity<Void> handle(@PathVariable Long employeeId) {
        service.handle(employeeId);
        return ResponseEntity.noContent().build();
    }
}
