package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.ProduitResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/produits")
@Tag(name = "Produit Endpoints", description = "Endpoints pour les produits")
public interface ProduitEndpoints {


    @Operation(description = "Récuperer la liste des livreurs")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/getProduits")
    Set<ProduitResponseDTO> getAllProducts();

    @PostMapping(value = "/import/porduits",consumes ={"multipart/from-data"} )
    void importCsv( );


}
