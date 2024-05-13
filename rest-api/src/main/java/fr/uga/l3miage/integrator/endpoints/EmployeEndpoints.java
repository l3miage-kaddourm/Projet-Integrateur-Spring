package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/employes")
public interface EmployeEndpoints {

    @Operation(description = "Récuperer la liste des livreurs")
    @ApiResponse(responseCode = "200",description = "la liste des livreurs a été récupérée avec succès")
    @ApiResponse(responseCode = "404",description = "Aucun livreur n'a été trouvé")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/livreurs")
    Set<EmployeResponseDTO> getAllLivreurs();


    @PostMapping(value = "/import/employe",consumes ={"multipart/from-data"} )
     void importCsv( );

}

