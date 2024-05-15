package fr.uga.l3miage.integrator.repositories;


import fr.uga.l3miage.integrator.models.CamionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CamionRepository extends JpaRepository<CamionEntity, String> {

    Set<CamionEntity> findAllBy();
}
