package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.endpoints.ProduitEndpoints;
import fr.uga.l3miage.integrator.models.ProduitEntity;
import fr.uga.l3miage.integrator.responses.ProduitResponseDTO;
import fr.uga.l3miage.integrator.services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ProduitController implements ProduitEndpoints {

    @Autowired
    private final ProduitService produitService;




    public Set<ProduitResponseDTO> getAllProducts() {
        return produitService.getAllProducts();
    }

    @PostConstruct
    public void importCsv() {
        try {
            produitService.importCsv();
        } catch (Exception e) {
            System.err.println("Failed to import employees from CSV: " + e.getMessage());
        }

    }
}
