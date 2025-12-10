package saam.challenge.api.modules.employees.deleteEmployee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employees")
@RestController
@RequestMapping("/employees")
public class DeleteEmployeeController {

    private final DeleteEmployeeService service;

    public DeleteEmployeeController(DeleteEmployeeService service) {
        this.service = service;
    }

    @DeleteMapping("/{employeeId}")
    @Operation(summary = "Deleta um funcionário")
    public ResponseEntity<Void> handle(@PathVariable Long employeeId) {
        service.handle(employeeId);
        return ResponseEntity.noContent().build();
    }
}
