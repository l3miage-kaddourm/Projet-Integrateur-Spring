package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.endpoints.CommandeEndpoints;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LigneEntity;
import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import fr.uga.l3miage.integrator.responses.EntrepotResponseDTO;
import fr.uga.l3miage.integrator.responses.LigneResponseDTO;
import fr.uga.l3miage.integrator.services.CommandeService;
import fr.uga.l3miage.integrator.services.EntrepotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@DependsOn("clientController")
public class CommandeController implements CommandeEndpoints {


    @Autowired
    private final CommandeService commandeService;


    @Override
    public Set<CommandeResponseDTO> getAllCommands() {

        return commandeService.getAllCommands();
    }


    @Override
    public Set<CommandeResponseDTO> getAllCommandsOuvertes() {

        return commandeService.getAllCommandsOuvertes();
    }

    @PostConstruct
    public void importCsv() {
        try {
            commandeService.importCsv();
        } catch (IOException e) {
            System.err.println("Failed to import employees from CSV: " + e.getMessage());
        }
    }
}
