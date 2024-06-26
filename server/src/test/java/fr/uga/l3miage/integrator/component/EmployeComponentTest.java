package fr.uga.l3miage.integrator.component;


import fr.uga.l3miage.integrator.components.EmployeComponent;
import fr.uga.l3miage.integrator.enums.Emploi;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundEmployeException;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.repositories.EmployeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmployeComponentTest {

    @Autowired
    private EmployeComponent employeComponent;
    @MockBean
    private EmployeRepository employeRepository;

    @Test
    void findAllLivreurs_whenNoLivreurFound_shouldThrowException(){
        // Given
        when(employeRepository.findAllByEmploi(Emploi.livreur)).thenReturn(Set.of());

        // Then - When
        assertThrows(NotFoundEmployeException.class, () -> employeComponent.findAllLivreurs());
    }

    @Test
    void findAllLivreurs_whenLivreurFound_shouldReturnLivreurSet() throws NotFoundEmployeException {
        // Given
        EmployeEntity livreur = EmployeEntity
                .builder()
                .emploi(Emploi.livreur)
                .build();

        when(employeRepository.findAllByEmploi(Emploi.livreur)).thenReturn(Set.of(livreur));

        // When - Then
        assertDoesNotThrow(() -> employeComponent.findAllLivreurs());
    }

    @Test
    void getPlanificateurs_whenNoPlanificateurFound_shouldThrowException() {
        // Given
        when(employeRepository.findAllByEmploi(Emploi.planificateur)).thenReturn(Set.of());

        // Then - When
        assertThrows(NotFoundEmployeException.class, () -> employeComponent.getPlanificateurs());
    }

    @Test
    void getPlanificateurs_whenPlanificateurFound_shouldReturnPlanificateurSet() throws NotFoundEmployeException {
        // Given
        EmployeEntity planificateur = EmployeEntity
                .builder()
                .emploi(Emploi.planificateur)
                .build();

        when(employeRepository.findAllByEmploi(Emploi.planificateur)).thenReturn(Set.of(planificateur));

        // When - Then
        assertDoesNotThrow(() -> employeComponent.getPlanificateurs());
    }

}
