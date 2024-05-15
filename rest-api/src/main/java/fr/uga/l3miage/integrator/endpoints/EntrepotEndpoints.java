package fr.uga.l3miage.integrator.endpoints;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entrepot")
@Tag(name = "Entrepot endpoints", description = "Endpoints pour les entrepots")
public interface EntrepotEndpoints {




    @PostMapping(value = "/import/entrepot",consumes ={"multipart/from-data"} )
     void importCsv( );
}
