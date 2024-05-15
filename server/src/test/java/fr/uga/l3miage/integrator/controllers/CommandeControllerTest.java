package fr.uga.l3miage.integrator.controllers;


import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import fr.uga.l3miage.integrator.services.CommandeService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
public class CommandeControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandeService commandeService;

    @Test
    void testGetAllCommandsSuccessfully() {
        // Given
        CommandeEntity commande1 = CommandeEntity.builder()
                .reference("CMD001")
                .build();

        CommandeEntity commande2 = CommandeEntity.builder()
                .reference("CMD002")
                .build();

        commandeRepository.save(commande1);
        commandeRepository.save(commande2);

        Set<CommandeResponseDTO> expectedResponse = commandeService.getAllCommands();

        // When
        ResponseEntity<Set<CommandeResponseDTO>> response = testRestTemplate.exchange(
                "/commande/commandes",
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                new ParameterizedTypeReference<Set<CommandeResponseDTO>>() {}
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.size(), response.getBody().size());
    }
}
