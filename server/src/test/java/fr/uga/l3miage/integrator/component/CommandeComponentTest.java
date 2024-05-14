package fr.uga.l3miage.integrator.component;


import fr.uga.l3miage.integrator.components.CommandeComponent;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCommandsException;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

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
}
