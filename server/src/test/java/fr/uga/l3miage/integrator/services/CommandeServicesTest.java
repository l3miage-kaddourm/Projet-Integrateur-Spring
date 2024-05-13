package fr.uga.l3miage.integrator.services;


import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
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
    private LivraisonRepository livraisonRepository;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private CommandeRepository commandeRepository;

    @SpyBean
    private CommandeMapper commandeMapper;


//    @Test
//    void createCommande_ShouldCreateCommande() {
//        // Given
//        Set<CommandeEntity> commandeEntities = new HashSet<>();
//        commandeEntities.add(CommandeEntity.builder().reference("REF1").build());
//        commandeEntities.add(CommandeEntity.builder().reference("REF2").build());
//
//        when(commandeRepository.findAllBy()).thenReturn(commandeEntities);
//
//        // When
//        Set<CommandeResponseDTO> commandes = commandeService.getAllCommands();
//
//        // Then
//        assertEquals(commandeEntities.size(), commandes.size());
//        verify(commandeRepository, times(1)).findAllBy();
//    }

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
