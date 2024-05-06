package fr.uga.l3miage.integrator.repositories;


import fr.uga.l3miage.integrator.models.EntrepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepotRepository extends JpaRepository<EntrepotEntity, String> {

    EntrepotEntity findByNom(String nom);
    EntrepotEntity findByNomIgnoreCase(String nom);

}
