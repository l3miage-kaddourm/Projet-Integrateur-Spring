package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/tournees")
public interface TourneeEndpoints {


    @Operation(description = "Create a new tournee")
    @ApiResponse(responseCode = "200",description = "Tournee created successfully")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    TourneeResponseDTO createTournee(@RequestBody TourneeCreateRequest request);

}
