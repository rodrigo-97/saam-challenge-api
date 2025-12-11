package saam.challenge.api.modules.employees.deactivateEmployee;

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
public class DeactivateEmployeeController {

    private final DeactivateEmployeeService service;

    public DeactivateEmployeeController(DeactivateEmployeeService service) {
        this.service = service;
    }

    @PatchMapping("/{employeeId}/deactivate")
    @Operation(summary = "Desativa um funcionário")
    public ResponseEntity<Void> handle(@PathVariable Long employeeId) {
        service.handle(employeeId);
        return ResponseEntity.noContent().build();
    }
}
