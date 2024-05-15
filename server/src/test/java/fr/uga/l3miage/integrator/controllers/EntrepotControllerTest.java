package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.responses.EntrepotResponseDTO;
import fr.uga.l3miage.integrator.services.EntrepotService;
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
public class EntrepotControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EntrepotRepository entrepotRepository;

    @Autowired
    private EntrepotService entrepotService;

//    @Test
//    void testGetAllEntrepotsSuccessfully() {
//        // Given
//        EntrepotEntity entrepot1 = EntrepotEntity.builder()
//                .nom("Entrepot1")
//                .lettre("A")
//                .photo("photo1.jpg")
//                .build();
//
//        EntrepotEntity entrepot2 = EntrepotEntity.builder()
//                .nom("Entrepot2")
//                .lettre("B")
//                .photo("photo2.jpg")
//                .build();
//
//        entrepotRepository.save(entrepot1);
//        entrepotRepository.save(entrepot2);
//
//        Set<EntrepotResponseDTO> expectedResponse = entrepotService.getAllEntrepots();
//
//        // When
//        ResponseEntity<Set<EntrepotResponseDTO>> response = testRestTemplate.exchange(
//                "/entrepot/entrepots",
//                HttpMethod.GET,
//                new HttpEntity<>(null, null),
//                new ParameterizedTypeReference<Set<EntrepotResponseDTO>>() {}
//        );
//
//        // Then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(expectedResponse.size(), response.getBody().size());
//    }
}
