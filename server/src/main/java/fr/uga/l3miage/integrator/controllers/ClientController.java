package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
@RequiredArgsConstructor

public class ClientController {

    @Autowired
    private final ClientService clientService;

    @PostConstruct
    public void importCsv() {
        try {
            clientService.importCsv();
        } catch (IOException e) {
            System.err.println("Failed to import employees from CSV: " + e.getMessage());
        }
    }

}
