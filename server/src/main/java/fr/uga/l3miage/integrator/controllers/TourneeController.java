package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.endpoints.TourneeEndpoints;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import fr.uga.l3miage.integrator.services.TourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TourneeController  implements TourneeEndpoints {

    @Autowired
    private final TourneeService tourneeService;

@Override
    public TourneeResponseDTO createTournee(TourneeCreateRequest request) {
        return tourneeService.createTournee(request);
    }
}
