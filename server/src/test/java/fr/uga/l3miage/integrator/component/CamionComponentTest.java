package fr.uga.l3miage.integrator.component;


import fr.uga.l3miage.integrator.components.CamionComponent;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCamionException;
import fr.uga.l3miage.integrator.models.CamionEntity;
import fr.uga.l3miage.integrator.repositories.CamionRepository;
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
public class CamionComponentTest {

    @Autowired
    private CamionComponent camionComponent;

    @MockBean
    private CamionRepository camionRepository;

    @Test
    void findAllCamions_whenNoCamionFound_shouldThrowException() {
        // Given
        when(camionRepository.findAll()).thenReturn(Collections.emptyList());

        // Then - When
        assertThrows(NotFoundCamionException.class, () -> camionComponent.findAllCamions());
    }


    @Test
    void findAllCamions_whenCamionFound_shouldReturnCamionSet() throws NotFoundCamionException {
        // Given
        CamionEntity camion = CamionEntity
                .builder()
                .immatriculation("ABC123")
                .build();

        Set<CamionEntity> mockCamions = new HashSet<>();
        mockCamions.add(camion);

        when(camionRepository.findAll()).thenReturn(List.of(camion));

        // When - Then
        assertDoesNotThrow(() -> {
            Set<CamionEntity> camions = camionComponent.findAllCamions();
            // Vérifiez que le set contient le camion attendu
            assertTrue(camions.contains(camion));
            assert camions.size() == 1;
            CamionEntity retrievedCamion = camions.iterator().next();
            assertTrue(retrievedCamion.getImmatriculation().equals("ABC123"));
        });
    }
}
