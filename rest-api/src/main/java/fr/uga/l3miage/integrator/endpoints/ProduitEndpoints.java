package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.CamionResponseDTO;
import fr.uga.l3miage.integrator.responses.ProduitResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Commande endpoints")
@CrossOrigin(origins = "http://localhost:8080")
public interface ProduitEndpoints {


    @Operation(description = "Récuperer la liste des livreurs")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/produits")
    Set<ProduitResponseDTO> getAllProducts();

    @PostMapping(value = "/import/porduits",consumes ={"multipart/from-data"} )
    void importCsv( );


}
