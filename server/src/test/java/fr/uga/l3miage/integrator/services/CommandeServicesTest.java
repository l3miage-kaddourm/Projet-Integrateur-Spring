package fr.uga.l3miage.integrator.services;


import fr.uga.l3miage.integrator.components.CommandeComponent;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundCommandsRestException;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCommandsException;
import fr.uga.l3miage.integrator.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.repositories.ClientRepository;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CommandeServicesTest {
    @Autowired
    private CommandeService commandeService;

    @MockBean
    private CommandeComponent commandeComponent;

    @MockBean
    private CommandeMapper commandeMapper;

    @Test
    void getAllCommands_whenCommandesFound_shouldReturnCommandeSet() throws NotFoundCommandsException{
        // Given
        CommandeEntity commande1 = CommandeEntity.builder()
                .reference("CMD001")
                .montant(250.0)
                .build();
        CommandeEntity commande2 = CommandeEntity.builder()
                .reference("CMD002")
                .montant(450.0)
                .build();
        List<CommandeEntity> commandes = List.of(commande1, commande2);

        CommandeResponseDTO response1 = new CommandeResponseDTO();
        response1.setReference("CMD001");
        CommandeResponseDTO response2 = new CommandeResponseDTO();
        response1.setReference("CMD001");
        Set<CommandeResponseDTO> expectedResponses = Set.of(response1, response2);

        when(commandeComponent.findAllCommandes()).thenReturn(commandes);
        when(commandeMapper.toCommandeResponseDTO(commande1)).thenReturn(response1);
        when(commandeMapper.toCommandeResponseDTO(commande2)).thenReturn(response2);

        // When
        Set<CommandeResponseDTO> actualResponses = commandeService.getAllCommands();

        // Then
        assertEquals(expectedResponses, actualResponses);
        verify(commandeComponent).findAllCommandes();
        verify(commandeMapper).toCommandeResponseDTO(commande1);
        verify(commandeMapper).toCommandeResponseDTO(commande2);
    }

    @Test
    void getAllCommands_whenNoCommandesFound_shouldThrowNotFoundCommandsRestException() throws NotFoundCommandsException {
        // Given
        when(commandeComponent.findAllCommandes()).thenThrow(new NotFoundCommandsException("Aucune Commande Trouvée"));

        // Then - When
        assertThrows(NotFoundCommandsRestException.class, () -> commandeService.getAllCommands(),
                "Expected getAllCommands() to throw NotFoundCommandsRestException, but it did not.");

        // Verify
        verify(commandeComponent).findAllCommandes(); // Ensure findAllCommandes() is called
        verifyNoInteractions(commandeMapper); // No mappings should occur when there are no commandes
    }

//    @Test
//    void importCsv_ShouldImportCsvFile() throws IOException {
//        // Given
//        when(commandeRepository.saveAll(anySet())).thenReturn(null); // Or any appropriate return value
//
//        // When
//        commandeService.importCsv();
//
//        // Then
//        verify(commandeRepository, times(1)).saveAll(anySet());
//    }
//
//    @Test
//    void importCsv_ShouldThrowException_WhenIOException() throws IOException {
//        // Given
//        doThrow(new IOException()).when(commandeRepository).saveAll(anySet());
//
//        // Then
//        assertThrows(CsvImportRestException.class, () -> {
//            commandeService.importCsv();
//        });
//    }

}
