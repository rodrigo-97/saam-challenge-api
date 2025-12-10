package saam.challenge.api.modules.employees.activateEmployee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employees")
@RestController
@RequestMapping("/employees")
public class ActivateUserController {

    private final ActivateUserService service;

    public ActivateUserController(ActivateUserService service) {
        this.service = service;
    }

    @PostMapping("/{employeeId}/activate")
    @Operation(summary = "Ativa um empregado")
    public ResponseEntity<Void> handle(@PathVariable Long employeeId) {
        service.handle(employeeId);
        return ResponseEntity.noContent().build();
    }
}
