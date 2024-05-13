package fr.uga.l3miage.integrator.repositories;


import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, String> {
    //Set<CommandeEntity> findAllByEtatOrderByDateAsc(EtatsDeCommande etat);
    //Set<CommandeEntity> findAllBy();
}
