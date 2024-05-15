package fr.uga.l3miage.integrator.repositories;


import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class CommandeRepositoryTest {
    @Autowired
    private CommandeRepository commandeRepository;

    @Test
    void testFindAllByEtat() {
        // Given
        CommandeEntity commande1 = CommandeEntity.builder()
                .reference("CMD001")
                .etat(EtatsDeCommande.ouverte)
                .build();

        CommandeEntity commande2 = CommandeEntity.builder()
                .reference("CMD002")
                .etat(EtatsDeCommande.livrée)
                .build();

        CommandeEntity commande3 = CommandeEntity.builder()
                .reference("CMD003")
                .etat(EtatsDeCommande.ouverte)
                .build();

        commandeRepository.save(commande1);
        commandeRepository.save(commande2);
        commandeRepository.save(commande3);

        // When
        Set<CommandeEntity> ouvertCommandeEntities = commandeRepository.findAllByEtat(EtatsDeCommande.ouverte);

        // Then
        assertThat(ouvertCommandeEntities).hasSize(57);
        assertThat(ouvertCommandeEntities).extracting(CommandeEntity::getReference).contains("CMD001", "CMD003");
    }

    @Test
    void testFindByReference() {
        // Given
        CommandeEntity commande = CommandeEntity.builder()
                .reference("CMD004")
                .etat(EtatsDeCommande.ouverte)
                .build();

        commandeRepository.save(commande);

        // When
        CommandeEntity foundCommande = commandeRepository.findByReference("CMD004").orElse(null);

        // Then
        assertThat(foundCommande).isNotNull();
        assertThat(foundCommande.getReference()).isEqualTo("CMD004");
        assertThat(foundCommande.getEtat()).isEqualTo(EtatsDeCommande.ouverte);
    }
}
