package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.endpoints.JourneeEndpoints;
import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.JourneeUpdateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import fr.uga.l3miage.integrator.services.JourneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@DependsOn("entrepotController")
public class JourneeController implements JourneeEndpoints {
        @Autowired
        private final JourneeService journeeService;

    @Override
    public JourneeResponseDTO updateJournee(String refJournee, JourneeCreateRequest request) {
        return journeeService.createJournee(request);
    }

    @Override
    public JourneeResponseDTO storeJournee(JourneeCreateRequest request) {
        return journeeService.createJournee(request);
    }



}
