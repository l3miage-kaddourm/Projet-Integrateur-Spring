package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.endpoints.EntrepotEndpoints;
import fr.uga.l3miage.integrator.responses.EntrepotResponseDTO;
import fr.uga.l3miage.integrator.services.EntrepotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class EntrepotController implements EntrepotEndpoints {

    @Autowired
    private final EntrepotService entrepotService;

    public EntrepotResponseDTO getEntrepotByName() {
        return entrepotService.findByNom("Grenis");
    }

    @PostConstruct
    public void importCsv() {
        try {
            entrepotService.importCsv();
        } catch (IOException e) {
            System.err.println("Failed to import employees from CSV: " + e.getMessage());
        }
    }
}
