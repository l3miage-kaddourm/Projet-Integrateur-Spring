package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.endpoints.EmployeEndpoints;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEmployeRestException;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import fr.uga.l3miage.integrator.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@DependsOn("entrepotController")
public class EmployeController implements EmployeEndpoints {


    @Autowired
    private final EmployeService employeService;

    public Set<EmployeResponseDTO> getAllLivreurs() {
        try {
            return employeService.getLivreurs();
        } catch (NotFoundEmployeRestException e) {
            return Collections.emptySet();
        }
    }

    public Set<EmployeResponseDTO> getPlanificateurs() {
        try {
            return employeService.getPlanificateurs();
        } catch (NotFoundEmployeRestException e) {
            return Collections.emptySet();
        }
    }

    @PostConstruct
    public void importCsv() {
        try {
            employeService.importCsv();
        } catch (IOException e) {
            System.err.println("Failed to import employees from CSV: " + e.getMessage());
        }
    }
}