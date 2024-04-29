package fr.uga.l3miage.integrator.errors;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeErrorResponse {
    @Schema(description = "Error message: End Point Call", example = "/employes/entrepot/1")
    private final String uri;
    @Schema(description = "Error message: Not Found", example = "Employe not found")
    private final String errorMessage;
}
