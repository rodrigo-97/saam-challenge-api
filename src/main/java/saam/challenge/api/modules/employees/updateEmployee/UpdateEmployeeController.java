package saam.challenge.api.modules.employees.updateEmployee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employees")
@RestController
@RequestMapping("/employees")
public class UpdateEmployeeController {

    private final UpdateEmployeeService service;

    public UpdateEmployeeController(UpdateEmployeeService service) {
        this.service = service;
    }

    @PutMapping("/{employeeId}")
    @Operation(summary = "Atualiza os dados de um funcionário")
    public ResponseEntity<Void> handle(@RequestBody @Valid UpdateEmployeeParams params, @PathVariable Long employeeId) {
        service.handle(employeeId, params);
        return ResponseEntity.noContent().build();
    }
}
