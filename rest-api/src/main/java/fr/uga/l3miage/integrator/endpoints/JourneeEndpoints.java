package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Commande endpoints")
@CrossOrigin(origins = "http://localhost:8080")
public interface JourneeEndpoints {



    @Operation(description = "Stocker une Journee")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/journee")
    JourneeResponseDTO storeJournee(@RequestBody JourneeCreateRequest request);
}
