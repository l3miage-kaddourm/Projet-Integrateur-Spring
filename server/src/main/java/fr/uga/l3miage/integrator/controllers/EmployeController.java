package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.components.EmployeComponent;
import fr.uga.l3miage.integrator.endpoints.EmployeEndPoints;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import fr.uga.l3miage.integrator.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class EmployeController implements EmployeEndPoints {

    private final EmployeComponent employeComponent;
    private final EmployeService employeService;

    public Set<EmployeResponseDTO> getAllLivreurs() {
        return employeComponent.finAllLivreurs().stream()
                .map(employeService::convertToDTO)
                .collect(Collectors.toSet());
    }
}