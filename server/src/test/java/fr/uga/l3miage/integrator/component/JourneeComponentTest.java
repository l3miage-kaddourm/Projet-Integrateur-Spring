package fr.uga.l3miage.integrator.component;

import fr.uga.l3miage.integrator.components.JourneeComponent;
import fr.uga.l3miage.integrator.components.LivraisonComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundJourneeException;
import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JourneeComponentTest {

    @Autowired
    private JourneeComponent journeeComponent;

    @MockBean
    private JourneeRepository journeeRepository;

    @MockBean
    private LivraisonRepository livraisonRepository;

    @MockBean
    private TourneeRepository tourneeRepository;

    @MockBean
    private JourneeMapper journeeMapper;

    @MockBean
    private LivraisonComponent livraisonComponent;

    public JourneeComponentTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void updateJournee_whenJourneeNotFound_shouldThrowException() {
        // Given
        String refJournee = "J001";
        JourneeEntity journeeUpdated = new JourneeEntity();
        when(journeeRepository.findById(refJournee)).thenReturn(Optional.empty());

        // Then - When
        assertThrows(NotFoundJourneeException.class, () -> journeeComponent.updateJournee(refJournee, journeeUpdated));
    }

    @Test
    void updateJournee_whenJourneeFoundAndUpdatedSuccessfully() throws NotFoundJourneeException {
        // Given
        TourneeEntity tourneeEntity = TourneeEntity.builder()
                .reference("T001")
                .etat(EtatsDeTournee.planifiee)
                .build();

        LivraisonEntity livraisonEntity = LivraisonEntity.builder()
                .reference("L001")
                .etat(EtatsDeLivraison.planifiee)
                .build();

        Set<LivraisonEntity> livraisons = new HashSet<>();
        livraisons.add(livraisonEntity);

        tourneeEntity.setLivraisons(livraisons);

        Set<TourneeEntity> tournees = new HashSet<>();
        tournees.add(tourneeEntity);

        JourneeEntity journeeEntity = JourneeEntity.builder()
                .reference("J001")
                .etat(EtatsDeJournee.planifiee)
                .tournees(tournees)
                .build();

        JourneeEntity journeeEntityUpdated = JourneeEntity.builder()
                .reference("J001")
                .etat(EtatsDeJournee.enCours)
                .build();

        when(journeeRepository.findById("J001")).thenReturn(Optional.of(journeeEntity));
        when(journeeRepository.save(journeeEntity)).thenReturn(journeeEntity);

        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Journée mise à jour avec succès", HttpStatus.OK);

        // When
        ResponseEntity<String> actualResponse = journeeComponent.updateJournee("J001", journeeEntityUpdated);

        // Then
        assertEquals(expectedResponse, actualResponse);
        assertEquals(EtatsDeJournee.enCours, journeeEntity.getEtat());

        verify(journeeRepository, times(1)).findById("J001");
        verify(journeeRepository, times(1)).save(journeeEntity);

        verify(tourneeRepository, times(1)).save(tourneeEntity);
        verify(livraisonRepository, times(1)).save(livraisonEntity);
    }
}