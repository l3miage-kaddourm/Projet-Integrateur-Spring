package fr.uga.l3miage.integrator.controllers;



import fr.uga.l3miage.integrator.endpoints.LigneEndpoints;
import fr.uga.l3miage.integrator.services.LigneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
@DependsOn({"produitController", "commandeController"})
public class LigneController implements LigneEndpoints {

    @Autowired
    LigneService ligneService;

    @PostConstruct
    public void importCsv() {
        try {
            ligneService.importCsv();
        } catch (IOException e) {
            System.err.println("Failed to import Lignes from CSV: " + e.getMessage());
        }
    }
}