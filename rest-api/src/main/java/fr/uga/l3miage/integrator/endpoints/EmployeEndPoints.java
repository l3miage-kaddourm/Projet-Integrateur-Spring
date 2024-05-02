package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.exceptions.technical.NotFoundLivreursException;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/employes")
public interface EmployeEndPoints {

    @Operation(description = "Récuperer la liste des livreurs")
    @ApiResponse(responseCode = "200",description = "la liste des livreurs a été récupérée avec succès",
            content = @Content(schema = @Schema(implementation = NotFoundLivreursException.class)))
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/livreurs")
    Set<EmployeResponseDTO> getAllLivreurs();
//    @Operation(description = "Importer les employés depuis un fichier CSV")
//    @ApiResponse(responseCode = "400",description = "Les employés ont été importés avec succès",
//            content = @Content(schema = @Schema(implementation = IOException.class)))
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/import")
//    void importEmployesFromCSV();

    @PostMapping(value = "/import",consumes ={"multipart/from-data"} )
     void importCsvSecond( );

}

