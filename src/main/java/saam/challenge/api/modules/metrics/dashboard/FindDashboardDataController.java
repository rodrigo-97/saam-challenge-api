package saam.challenge.api.modules.metrics.dashboard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Metrics")
@RestController
@RequestMapping("/metrics")
public class FindDashboardDataController {

    private final FindDashboardDataService service;

    public FindDashboardDataController(FindDashboardDataService service) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Busca dados para preencher dashboard")
    public ResponseEntity<FindDashboardDataReturn> handle() {
        FindDashboardDataReturn returnData = service.handle();
        return ResponseEntity.status(HttpStatus.OK).body(returnData);
    }
}
