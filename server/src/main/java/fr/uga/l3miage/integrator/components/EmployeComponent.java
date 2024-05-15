package fr.uga.l3miage.integrator.components;


import fr.uga.l3miage.integrator.enums.Emploi;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundEmployeException;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.repositories.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmployeComponent {

    private final EmployeRepository employeRepository;


    public Set<EmployeEntity> findAllLivreurs() throws NotFoundEmployeException {

        Set<EmployeEntity> livreurs=employeRepository.findAllByEmploi(Emploi.livreur);
        if(livreurs.isEmpty())
            throw new NotFoundEmployeException("Aucun Livreur Trouvé");
        return livreurs;
    }

    public Set<EmployeEntity> getPlanificateurs() throws NotFoundEmployeException{
        Set<EmployeEntity> planificateurs=employeRepository.findAllByEmploi(Emploi.planificateur);
        if(planificateurs.isEmpty())
            throw new NotFoundEmployeException("Aucun Planificateur Trouvé");
        return planificateurs;
    }
}
