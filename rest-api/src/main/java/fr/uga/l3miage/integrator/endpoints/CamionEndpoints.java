package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.CamionResponseDTO;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Camion Endpoints", description = "Endpoints pour les camions")
@CrossOrigin(origins = "http://localhost:8080")
public interface CamionEndpoints {


    @Operation(description = "Récuperer la liste des livreurs")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/camions")
    Set<CamionResponseDTO> getAllCamions();



    @PostMapping(value = "/import/camions",consumes ={"multipart/from-data"} )
    void importCsv( );
}
