package saam.challenge.api.modules.employees.findEmployees;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saam.challenge.api.shared.returnData.Paginated;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Employees")
@RestController
@RequestMapping("/employees")
public class FindEmployeesController {

    private final FindEmployeesService service;

    public FindEmployeesController(FindEmployeesService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Busca os dados dos funcionários de forma paginada")
    public ResponseEntity<Paginated<FindEmployeesReturn>> handle(@Valid @ParameterObject FindEmployeesParams params) {
        Paginated<FindEmployeesReturn> returnData = service.handle(params);
        return ResponseEntity.status(HttpStatus.OK).body(returnData);
    }
}
