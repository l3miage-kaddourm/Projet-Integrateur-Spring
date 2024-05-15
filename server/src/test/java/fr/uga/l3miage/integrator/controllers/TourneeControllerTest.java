package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import fr.uga.l3miage.integrator.services.TourneeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class TourneeControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TourneeRepository tourneeRepository;

    @Autowired
    private JourneeRepository journeeRepository;

    @Test
    void testCreateTourneeSuccessfully() {
        // Given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JourneeCreateRequest journeeRequest = JourneeCreateRequest.builder()
                .reference("2023-05-15")
                .build();

        TourneeCreateRequest request = TourneeCreateRequest.builder()
                .reference("T-003")
                .etat(EtatsDeTournee.planifiee)
                .lettre("C")
                .montant(1700.0)
                .distanceAparcourir(70.0)
                .journee(journeeRequest)
                .build();

        HttpEntity<TourneeCreateRequest> entity = new HttpEntity<>(request, headers);

        // When
        ResponseEntity<TourneeResponseDTO> response = testRestTemplate.postForEntity(
                "/tournees/create",
                entity,
                TourneeResponseDTO.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("T-003", response.getBody().getReference());
        assertEquals(1700.0, response.getBody().getMontant());

        // Verify that the Tournee is saved
        assertTrue(tourneeRepository.findById("T-003").isPresent());
    }
}