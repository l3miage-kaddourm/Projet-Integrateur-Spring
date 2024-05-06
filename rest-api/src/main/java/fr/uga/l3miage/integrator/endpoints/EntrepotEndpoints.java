package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.EntrepotResponseDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/entrepots")
public interface EntrepotEndpoints {


    @RequestMapping("/Grenis")
    EntrepotResponseDTO getEntrepotByName();

    @PostMapping(value = "/import",consumes ={"multipart/from-data"} )
     void importCsv( );
}
