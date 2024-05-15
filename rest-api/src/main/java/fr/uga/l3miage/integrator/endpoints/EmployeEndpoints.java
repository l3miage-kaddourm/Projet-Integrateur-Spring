package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("/employe")
@Tag(name = "Employe endpoints", description = "Endpoints pour les employes")
public interface EmployeEndpoints {

    @Operation(description = "Récuperer la liste des livreurs")
    @ApiResponse(responseCode = "200",description = "la liste des livreurs a été récupérée avec succès")
    @ApiResponse(responseCode = "404",description = "Aucun livreur n'a été trouvé")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/livreurs")
    Set<EmployeResponseDTO> getAllLivreurs();

    @Operation(description = "Récuperer la liste des planificateurs")
    @ApiResponse(responseCode = "200",description = "la liste des planificateurs a été récupérée avec succès")
    @ApiResponse(responseCode = "404",description = "Aucun planificateur n'a été trouvé")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/planificateurs")
    Set<EmployeResponseDTO> getPlanificateurs();


    @PostMapping(value = "/import/employe",consumes ={"multipart/from-data"} )
     void importCsv( );

}

