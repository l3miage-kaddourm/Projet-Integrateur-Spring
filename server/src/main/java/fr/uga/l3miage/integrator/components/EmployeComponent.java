package fr.uga.l3miage.integrator.components;


import fr.uga.l3miage.integrator.enums.Emploi;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.repositories.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmployeComponent {

    private final EmployeRepository employeRepository;


    public Set<EmployeEntity> finAllLivreurs() {

        return employeRepository.findAllByEmploi(Emploi.livreur);
    }
}
