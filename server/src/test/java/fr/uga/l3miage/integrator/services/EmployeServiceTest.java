package fr.uga.l3miage.integrator.services;
import fr.uga.l3miage.integrator.components.EmployeComponent;
import fr.uga.l3miage.integrator.enums.Emploi;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundLivreursRestException;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundLivreursException;
import fr.uga.l3miage.integrator.mappers.EmployeMapper;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeServiceTest {

    @MockBean
    private EmployeComponent employeComponent;

    @MockBean
    private EmployeMapper employeMapper;

    @Autowired
    private EmployeService employeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLivreurs_ShouldReturnLivreurs_WhenTheyExist() throws NotFoundLivreursException {
        // Given
        Set<EmployeEntity> mockEmployes = new HashSet<>();
        EmployeEntity employe = new EmployeEntity("trigramme1", "email@example.com", "John", "Doe", "photo1", "1234567890", Emploi.livreur, new EntrepotEntity());
        employe.getEntrepot().setNom("Entrepot A");
        mockEmployes.add(employe);
        EmployeResponseDTO expectedDto = new EmployeResponseDTO("trigramme1", "email@example.com", "John", "Doe", "photo1", "1234567890", Emploi.livreur, "Entrepot A");

        when(employeComponent.findAllLivreurs()).thenReturn(mockEmployes);
        when(employeMapper.convertToDTO(any(EmployeEntity.class))).thenReturn(expectedDto);

        // When
        Set<EmployeResponseDTO> result = employeService.getLivreurs();

        // Then
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        EmployeResponseDTO actualDto = result.iterator().next();
        // Assertions to compare each field
    }


    @Test
    public void getLivreurs_ShouldThrowNotFoundLivreursRestException_WhenNoLivreursFound() throws NotFoundLivreursException {
        // Given
        when(employeComponent.findAllLivreurs()).thenThrow(new NotFoundLivreursException("Aucun Livreur Trouvé"));

        // When & Then
        NotFoundLivreursRestException thrown = assertThrows(
                NotFoundLivreursRestException.class,
                () -> employeService.getLivreurs(),
                "Expected getLivreurs() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Aucun Livreur Trouvé"));
    }

}