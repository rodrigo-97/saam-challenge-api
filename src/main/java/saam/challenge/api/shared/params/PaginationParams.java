package saam.challenge.api.shared.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationParams {

    @NotNull(message = "O número da página não pode ser nulo.")
    @Positive(message = "O número da página deve ser positivo (e.g., 1, 2, 3...).")
    @Schema(description = "Página a ser buscada", example = "1")
    private Integer page = 1;

    @NotNull(message = "O tamanho da página (size) não pode ser nulo.")
    @Positive(message = "O tamanho da página deve ser um número positivo de itens por página.")
    @Min(value = 5, message = "O tamanho da página deve ser no mínimo {value} itens.")
    @Max(value = 100, message = "O tamanho da página deve ser no máximo {value} itens.")
    @Schema(description = "Tamanho da página (quantidade de registros por página)", example = "25")
    private Integer size = 25;
}
