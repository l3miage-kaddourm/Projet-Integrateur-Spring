package fr.uga.l3miage.integrator.endpoints;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ligne")
@Tag(name = "Ligne endpoints", description = "Endpoints pour les lignes")
public interface LigneEndpoints {

    @PostMapping(value = "/import/Lignes",consumes ={"multipart/from-data"} )
    void importCsv( );

}
