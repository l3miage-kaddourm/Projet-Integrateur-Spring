package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.endpoints.CamionEndpoints;
import fr.uga.l3miage.integrator.responses.CamionResponseDTO;
import fr.uga.l3miage.integrator.services.CamionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@DependsOn("entrepotController")
public class CamionController implements CamionEndpoints {
    @Autowired
    private final CamionService camionService;


   public Set<CamionResponseDTO> getAllCamions() {
        return camionService.getAllCamions();
    }

    @PostConstruct
    public void importCsv() {
        try {
            camionService.importCsv();
        } catch (IOException e) {
            System.err.println("Failed to import employees from CSV: " + e.getMessage());
        }
    }
}
