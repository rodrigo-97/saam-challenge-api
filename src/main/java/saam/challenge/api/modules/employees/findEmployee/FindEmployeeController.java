package saam.challenge.api.modules.employees.findEmployee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employees")
@RestController
@RequestMapping("/employees")
public class FindEmployeeController {

    private final FindEmployeeService service;

    public FindEmployeeController(FindEmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{employeeId}")
    @Operation(summary = "Busca os dados de um funcionário pelo ID")
    public ResponseEntity<FindEmployeeReturn> handle(@PathVariable Long employeeId) {
        FindEmployeeReturn returnData = service.handle(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(returnData);
    }
}
