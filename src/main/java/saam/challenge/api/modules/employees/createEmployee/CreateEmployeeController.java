package saam.challenge.api.modules.employees.createEmployee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employees")
@RestController
@RequestMapping("/employees")
public class CreateEmployeeController {

    private final CreateEmployeeService service;

    public CreateEmployeeController(CreateEmployeeService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cria um novo funcionário")
    public ResponseEntity<CreateEmployeeReturn> handle(@RequestBody @Valid CreateEmployeeParams params) {
        CreateEmployeeReturn returnData = service.handle(params);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnData);
    }
}