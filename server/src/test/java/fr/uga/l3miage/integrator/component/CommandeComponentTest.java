package fr.uga.l3miage.integrator.component;


import fr.uga.l3miage.integrator.components.CommandeComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCommandsException;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CommandeComponentTest {
    @Autowired
    private CommandeComponent commandeComponent;

    @MockBean
    private CommandeRepository commandeRepository;

    @Test
    void findAllCommandes_whenNoCommandeFound_shouldThrowException() {
        // Given
        when(commandeRepository.findAll()).thenReturn(Collections.emptyList());

        // Then - When
        assertThrows(NotFoundCommandsException.class, () -> commandeComponent.findAllCommandes());
    }

    @Test
    void findAllCommandes_whenCommandeFound_shouldReturnCommandeList() throws NotFoundCommandsException {
        // Given
        CommandeEntity commande = new CommandeEntity(); // Assume CommandeEntity has necessary fields initialized here
        List<CommandeEntity> commandes = List.of(commande);

        when(commandeRepository.findAll()).thenReturn(commandes);

        // When - Then
        assertDoesNotThrow(() -> commandeComponent.findAllCommandes());
    }

    @Test
    void findAllcommandsouverte_whenNoCommandeOuverteFound_shouldThrowException() {
        // Given
        when(commandeRepository.findAllByEtat(EtatsDeCommande.ouverte)).thenReturn(Collections.emptySet());

        // Then - When
        assertThrows(NotFoundCommandsException.class, () -> commandeComponent.findAllcommandsouverte());
    }

    @Test
    void findAllcommandsouverte_whenCommandeOuverteFound_shouldReturnCommandeSet() throws NotFoundCommandsException {
        // Given
        CommandeEntity commande = CommandeEntity.builder().etat(EtatsDeCommande.ouverte).build();
        Set<CommandeEntity> commandes = new HashSet<>(Set.of(commande));

        when(commandeRepository.findAllByEtat(EtatsDeCommande.ouverte)).thenReturn(commandes);

        // When
        Set<CommandeEntity> result = commandeComponent.findAllcommandsouverte();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(commande));
    }
}
