package fr.uga.l3miage.integrator.repositories;


import fr.uga.l3miage.integrator.models.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProduitRepository extends JpaRepository<ProduitEntity, Long> {

    Set<ProduitEntity> findAllBy();

    Optional<ProduitEntity> findByReference(String reference);
}
