package fr.uga.l3miage.integrator.repositories;


import fr.uga.l3miage.integrator.enums.Emploi;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeRepository extends JpaRepository<EmployeEntity, String> {

        Set<EmployeEntity> findAllByEmploi(Emploi emploi);
        EmployeEntity findByTrigramme(String trigramme);
//        Set<EmployeEntity> findAllByTournees_Reference(String reference);

}

