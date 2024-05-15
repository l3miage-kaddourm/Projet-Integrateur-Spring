package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
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
@Tag(name = "Commande endpoints" , description = "Endpoints pour les commandes")
@RequestMapping("/commande")
public interface CommandeEndpoints {


   @Operation(description = "Récuperer la liste des livreurs")
    @ApiResponse(responseCode = "200",description = "la liste des livreurs a été récupérée avec succès")
   @ApiResponse(responseCode = "408",description = "Aucune commande n'a été trouvée")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/ouvertes")
    Set<CommandeResponseDTO> getAllCommandsOuvertes();

    @Operation(description = "Récuperer la liste des livreurs")
    @ApiResponse(responseCode = "200",description = "la liste des livreurs a été récupérée avec succès")
    @ApiResponse(responseCode = "408",description = "Aucune commande n'a été trouvée")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/commandes")
    Set<CommandeResponseDTO> getAllCommands();


    @PostMapping(value = "/import/commande",consumes ={"multipart/from-data"} )
    void importCsv( );

}
