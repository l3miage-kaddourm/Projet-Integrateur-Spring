package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.LigneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LigneRepository extends JpaRepository<LigneEntity, Long> {


    Optional<LigneEntity> findById(String id);
}
