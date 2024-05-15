package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journees")
@Tag(name = "Journee endpoints", description = "Endpoints pour les journees")
public interface JourneeEndpoints {



    @Operation(description = "Stock a Journee")
    @ApiResponse(responseCode = "200", description = "Journee updated")
    @ApiResponse(responseCode = "503", description = "Journee not found")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/journeeCreate")
    JourneeResponseDTO storeJournee(@RequestBody JourneeCreateRequest request);


    @Operation(description = "Update a Journee")
    @ApiResponse(responseCode = "200", description = "Journee updated")
    @ApiResponse(responseCode = "503", description = "Journee not found")
    @RequestMapping("/journeeUpdate/{refJournee}")
    JourneeResponseDTO updateJournee(@PathVariable String refJournee, @RequestBody JourneeCreateRequest request);
}
