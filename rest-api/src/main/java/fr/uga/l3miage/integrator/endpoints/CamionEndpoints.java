package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.CamionResponseDTO;
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
@Tag(name = "Camion Endpoints", description = "Endpoints pour les camions")
@RequestMapping("/camions")
public interface CamionEndpoints {


    @Operation(description = "Récuperer la liste des livreurs")
    @ApiResponse(responseCode = "200", description = "Liste des Camions récupérée avec succès")
    @ApiResponse(responseCode = "490", description = "Aucun Camion trouvé")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/getCamions")
    Set<CamionResponseDTO> getAllCamions();



    @PostMapping(value = "/import/camions",consumes ={"multipart/from-data"} )
    void importCsv( );
}
