package fr.uga.l3miage.integrator.endpoints;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Ligne endpoints")
@CrossOrigin(origins = "http://localhost:8080")
public interface LigneEndpoints {

    @PostMapping(value = "/import/Lignes",consumes ={"multipart/from-data"} )
    void importCsv( );

}
