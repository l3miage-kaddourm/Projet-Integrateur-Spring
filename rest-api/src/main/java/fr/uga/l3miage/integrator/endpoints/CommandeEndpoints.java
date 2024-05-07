package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.exceptions.technical.NotFoundLivreursException;
import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public interface CommandeEndpoints {


    @Operation(description = "Récuperer la liste des livreurs")
    @ApiResponse(responseCode = "200",description = "la liste des livreurs a été récupérée avec succès",
            content = @Content(schema = @Schema(implementation = NotFoundLivreursException.class)))
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/commandes")
    Set<CommandeResponseDTO> getAllCommands();


    @PostMapping(value = "/import",consumes ={"multipart/from-data"} )
    void importCsv( );

}
