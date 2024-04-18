package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.components.EmployeComponent;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeController {

    private final EmployeComponent employeComponent;

    public EmployeController(EmployeComponent employeComponent) {
        this.employeComponent = employeComponent;
    }

    @GetMapping("/livreurs")
    public Set<EmployeEntity> getAllLivreurs() {
        return employeComponent.finAllLivreurs();
    }
}