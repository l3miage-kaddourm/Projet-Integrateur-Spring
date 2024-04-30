package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.EmployeEndPoints;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundLivreursRestException;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import fr.uga.l3miage.integrator.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class EmployeController implements EmployeEndPoints {


    @Autowired
    private final EmployeService employeService;

    public Set<EmployeResponseDTO> getAllLivreurs() {
        try {
            return employeService.getLivreurs();
        } catch (NotFoundLivreursRestException e) {
            return Collections.emptySet();
        }
    }

    @PostConstruct
    public void importCsvSecond() {
        try {
            employeService.importCsvSecond();
        } catch (IOException e) {
            System.err.println("Failed to import employees from CSV: " + e.getMessage());
        }
    }
}