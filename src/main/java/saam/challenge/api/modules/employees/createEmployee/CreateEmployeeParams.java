package saam.challenge.api.modules.employees.createEmployee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateEmployeeParams {

    @NotEmpty(message = "O campo nome é obrigatório")
    @Size(max = 255, message = "O nome pode conter no máximo 255 caracteres")
    @Schema(description = "Nome do funcionário", example = "João Silva")
    private String name;

    @NotNull(message = "O campo data de admissão é obrigatório")
    @Schema(description = "Data de admissão do funcionário", example = "2025-12-09T08:30:00+00:00")
    private OffsetDateTime admissionDate;

    @Positive(message = "O valor do salário precisa ser maior do que zero")
    @NotNull(message = "O campo salário é obrigatório")
    @Schema(description = "Salário do funcionário", example = "3500.50")
    private BigDecimal salary;

    @NotNull(message = "O campo ativo é obrigatório")
    @Schema(description = "Se o funcionário está ativo", example = "true")
    private Boolean active;
}
