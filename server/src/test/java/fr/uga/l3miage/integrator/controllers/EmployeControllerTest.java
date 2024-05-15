package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.enums.Emploi;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.repositories.EmployeRepository;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import fr.uga.l3miage.integrator.services.EmployeService;
import org.junit.jupiter.api.BeforeEach;
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

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
public class EmployeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EmployeService employeService;

    @BeforeEach
    void setUp() {
        employeRepository.deleteAll();
    }

    @Test
    void testGetAllLivreursSuccessfully() {
        // Given
        EmployeEntity employe1 = EmployeEntity.builder()
                .trigramme("EBD")
                .email("elisa.berand@example.com")
                .prenom("Elisa")
                .nom("BERAND")
                .photo("Selection_999(635).jpg")
                .telephone("0678541296")
                .emploi(Emploi.planificateur)
                .build();

        EmployeEntity employe2 = EmployeEntity.builder()
                .trigramme("SWL")
                .email("sophie.wheel@example.com")
                .prenom("Sophie")
                .nom("WHEEL")
                .photo("Selection_999(634).jpg")
                .telephone("0685698568")
                .emploi(Emploi.livreur)
                .build();

        EmployeEntity employe3 = EmployeEntity.builder()
                .trigramme("MPK")
                .email("marja.polik@example.com")
                .prenom("Marja")
                .nom("POLIK")
                .photo("Selection_999(633).jpg")
                .telephone("0612885685")
                .emploi(Emploi.livreur)
                .build();

        EmployeEntity employe4 = EmployeEntity.builder()
                .trigramme("CMJ")
                .email("citeb.maruj@example.com")
                .prenom("Citeb")
                .nom("MARUJ")
                .photo("Selection_999(632).jpg")
                .telephone("0657477854")
                .emploi(Emploi.livreur)
                .build();

        employeRepository.save(employe1);
        employeRepository.save(employe2);
        employeRepository.save(employe3);
        employeRepository.save(employe4);

        Set<EmployeResponseDTO> expectedResponse = employeService.getLivreurs();

        // When
        ResponseEntity<Set<EmployeResponseDTO>> response = testRestTemplate.exchange(
                "/employe/livreurs",
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                new ParameterizedTypeReference<Set<EmployeResponseDTO>>() {}
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.size(), response.getBody().size());
    }

    @Test
    void testGetAllPlanificateursSuccessfully() {
        // Given
        EmployeEntity employe1 = EmployeEntity.builder()
                .trigramme("EBD")
                .email("elisa.berand@example.com")
                .prenom("Elisa")
                .nom("BERAND")
                .photo("Selection_999(635).jpg")
                .telephone("0678541296")
                .emploi(Emploi.planificateur)
                .build();

        EmployeEntity employe2 = EmployeEntity.builder()
                .trigramme("SWL")
                .email("sophie.wheel@example.com")
                .prenom("Sophie")
                .nom("WHEEL")
                .photo("Selection_999(634).jpg")
                .telephone("0685698568")
                .emploi(Emploi.planificateur)
                .build();

        EmployeEntity employe3 = EmployeEntity.builder()
                .trigramme("MPK")
                .email("marja.polik@example.com")
                .prenom("Marja")
                .nom("POLIK")
                .photo("Selection_999(633).jpg")
                .telephone("0612885685")
                .emploi(Emploi.livreur)
                .build();

        EmployeEntity employe4 = EmployeEntity.builder()
                .trigramme("CMJ")
                .email("citeb.maruj@example.com")
                .prenom("Citeb")
                .nom("MARUJ")
                .photo("Selection_999(632).jpg")
                .telephone("0657477854")
                .emploi(Emploi.livreur)
                .build();

        employeRepository.save(employe1);
        employeRepository.save(employe2);
        employeRepository.save(employe3);
        employeRepository.save(employe4);

        Set<EmployeResponseDTO> expectedResponse = employeService.getPlanificateurs();

        // When
        ResponseEntity<Set<EmployeResponseDTO>> response = testRestTemplate.exchange(
                "/employe/planificateurs",
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                new ParameterizedTypeReference<Set<EmployeResponseDTO>>() {}
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.size(), response.getBody().size());
    }


    @Test
    void testGetEmployeByTrigrammeNotFound() {
        // Given
        String trigramme = "XYZ";

        // When
        ResponseEntity<EmployeResponseDTO> response = testRestTemplate.exchange(
                "/employe/{trigramme}",
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                EmployeResponseDTO.class,
                trigramme
        );

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testImportCsv() throws IOException {
        // Given
        // Supposons que le fichier CSV contient des employÃ©s avec les trigrammes "EBD", "SWL", "MPK" et "CMJ".

        // When
        employeService.importCsv();

        // Then
        Set<EmployeResponseDTO> employes = employeService.getLivreurs();
        assertThat(employes).isNotEmpty();
        assertThat(employes).extracting("trigramme").contains("EBD", "SWL", "MPK", "CMJ");
    }
}