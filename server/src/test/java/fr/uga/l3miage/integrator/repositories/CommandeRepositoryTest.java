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
    void testRequestfindAllBy() {
        //given
        CommandeEntity commandeEntity = CommandeEntity
                .builder()
                .reference("IDE120D")
                .etat(EtatsDeCommande.ouverte)
                .build();

        CommandeEntity commandeEntity1 = CommandeEntity
                .builder()
                .reference("IHD120D23")
                .etat(EtatsDeCommande.ouverte)
                .build();

        CommandeEntity commandeEntity2 = CommandeEntity
                .builder()
                .reference("IHE120D1")
                .etat(EtatsDeCommande.ouverte)
                .build();


        commandeRepository.save(commandeEntity);
        commandeRepository.save(commandeEntity1);
        commandeRepository.save(commandeEntity2);

        //when
        Set<CommandeEntity> commandeResponses = commandeRepository.findAllByEtatOrderByDateAsc(EtatsDeCommande.ouverte);

        //then
        assertThat(commandeResponses).hasSize(3);
    }
}
