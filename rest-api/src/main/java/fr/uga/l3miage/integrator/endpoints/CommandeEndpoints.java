package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Commande endpoints")
@CrossOrigin(origins = "http://localhost:8080")
public interface CommandeEndpoints {


   @Operation(description = "Récuperer la liste des livreurs")
    @ApiResponse(responseCode = "200",description = "la liste des livreurs a été récupérée avec succès")
   @ApiResponse(responseCode = "408",description = "Aucune commande n'a été trouvée")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/commandes")
    Set<CommandeResponseDTO> getAllCommands();


    @PostMapping(value = "/import/commande",consumes ={"multipart/from-data"} )
    void importCsv( );

}
