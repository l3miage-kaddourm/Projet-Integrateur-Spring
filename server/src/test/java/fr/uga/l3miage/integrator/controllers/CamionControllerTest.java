package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.models.CamionEntity;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.repositories.CamionRepository;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.responses.CamionResponseDTO;
import fr.uga.l3miage.integrator.services.CamionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
public class CamionControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CamionRepository camionRepository;

    @Autowired
    private EntrepotRepository entrepotRepository;

    @Autowired
    private CamionService camionService;

    @Test
    void testGetAllCamionsSuccessfully() {
        // Given
        EntrepotEntity entrepot1 = EntrepotEntity.builder().nom("Entrepot1").build();
        EntrepotEntity entrepot2 = EntrepotEntity.builder().nom("Entrepot2").build();
        entrepotRepository.save(entrepot1);
        entrepotRepository.save(entrepot2);

        GeoPosition position1 = new GeoPosition();
        position1.setLaltitude(45.764043);
        position1.setLongitude(4.835659);

        GeoPosition position2 = new GeoPosition();
        position2.setLaltitude(48.856613);
        position2.setLongitude(2.352222);

        CamionEntity camion1 = CamionEntity.builder()
                .immatriculation("ABC123")
                .position(position1)
                .entrepot(entrepot1)
                .build();

        CamionEntity camion2 = CamionEntity.builder()
                .immatriculation("XYZ789")
                .position(position2)
                .entrepot(entrepot2)
                .build();

        camionRepository.save(camion1);
        camionRepository.save(camion2);

        Set<CamionResponseDTO> expectedResponse = camionService.getAllCamions();

        // When
        ResponseEntity<Set<CamionResponseDTO>> response = testRestTemplate.exchange("/camions/getCamions",
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                new ParameterizedTypeReference<Set<CamionResponseDTO>>() {}
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.size(), response.getBody().size());
    }
}