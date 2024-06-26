package fr.uga.l3miage.integrator.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fr.uga.l3miage.integrator.enums.Emploi;
import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.Optional;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourneeServiceTest {

    @Autowired
    private TourneeService tourneeService;

    @MockBean
    private TourneeRepository tourneeRepository;

    @MockBean
    private JourneeRepository journeeRepository;

    @MockBean
    private TourneeMapper tourneeMapper;

    @MockBean
    private JourneeMapper journeeMapper;

    @Test
    void createTournee() {
        // Given
        JourneeCreateRequest journeeRequest = JourneeCreateRequest.builder()
                .reference("2023-05-13")
                .build();

        TourneeCreateRequest request = TourneeCreateRequest.builder()
                .reference("T-001")
                .etat(EtatsDeTournee.planifiee)
                .lettre("A")
                .montant(1500.0)
                .tdmTheorique(480)
                .tdmEffective(450)
                .distanceAparcourir(50.0)
                .distanceDeRetour(50.0)
                .journee(journeeRequest)
                .build();

        JourneeEntity journeeEntity = JourneeEntity.builder()
                .reference("2023-05-13")
                .date(new Date())
                .distanceAParcourir(100.0)
                .montant(1500.0)
                .build();
        when(journeeRepository.findById("2023-05-13")).thenReturn(Optional.of(journeeEntity));

        TourneeEntity tourneeEntity = new TourneeEntity();
        when(tourneeMapper.toEntity(request)).thenReturn(tourneeEntity);

        when(tourneeRepository.save(tourneeEntity)).thenReturn(tourneeEntity);

        TourneeResponseDTO expectedResponse = new TourneeResponseDTO();
        when(tourneeMapper.toResponseDTO(tourneeEntity)).thenReturn(expectedResponse);

        // When
        TourneeResponseDTO actualResponse = tourneeService.createTournee(request);

        // Then
        assertThat(actualResponse).isSameAs(expectedResponse);
        verify(journeeRepository).findById("2023-05-13");
        verify(tourneeMapper).toEntity(request);
        verify(tourneeRepository).save(tourneeEntity);
        verify(tourneeMapper).toResponseDTO(tourneeEntity);
    }

    @Test
    void canNotCreateTournee() {
        // Given
        JourneeCreateRequest journeeRequest = JourneeCreateRequest.builder()
                .reference("2023-05-14")
                .build();

        TourneeCreateRequest request = TourneeCreateRequest.builder()
                .reference("T-002")
                .etat(EtatsDeTournee.planifiee)
                .lettre("B")
                .montant(1600.0)
                .tdmTheorique(500)
                .tdmEffective(460)
                .distanceAparcourir(60.0)
                .distanceDeRetour(60.0)
                .journee(journeeRequest)
                .build();

        JourneeEntity journeeEntity = JourneeEntity.builder()
                .reference("2023-05-14")
                .date(new Date())
                .distanceAParcourir(100.0)
                .montant(1600.0)
                .build();

        when(journeeRepository.findById("2023-05-14")).thenReturn(Optional.of(journeeEntity));

        TourneeEntity tourneeEntity = new TourneeEntity();
        when(tourneeMapper.toEntity(request)).thenReturn(tourneeEntity);

        RuntimeException exception = new RuntimeException("Database error");
        when(tourneeRepository.save(tourneeEntity)).thenThrow(exception);

        // When
        Exception thrownException = assertThrows(RuntimeException.class, () -> {
            tourneeService.createTournee(request);
        });

        // Then
        assertThat(thrownException.getMessage()).isEqualTo("Error while creating tournee");
        verify(journeeRepository).findById("2023-05-14");
        verify(tourneeMapper).toEntity(request);
        verify(tourneeRepository).save(tourneeEntity);
    }
}