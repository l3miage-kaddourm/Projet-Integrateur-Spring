package fr.uga.l3miage.integrator.component;


import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourneeComponentTest {

    @Autowired
    private TourneeComponent tourneeComponent;

    @MockBean
    private TourneeRepository tourneeRepository;

    @Test
    void createTournee_whenTourneeEntityValid_shouldNotThrowException() {
        // Given
        TourneeEntity tourneeEntity = new TourneeEntity();
        when(tourneeRepository.save(tourneeEntity)).thenReturn(tourneeEntity);

        // When - Then
        assertDoesNotThrow(() -> tourneeComponent.createTournee(tourneeEntity));
    }

    // Dans ces tests, le premier vérifie que la fonction createTournee() ne lance pas d'exception lorsque
    // l'entité de tournée est valide et le deuxième vérifie qu'une RuntimeException est lancée si
    // le TourneeRepository lance une exception lors de la sauvegarde.

    @Test
    void createTournee_whenTourneeRepositoryThrowsException_shouldThrowRuntimeException() {
        // Given
        TourneeEntity tourneeEntity = new TourneeEntity();
        when(tourneeRepository.save(tourneeEntity)).thenThrow(new RuntimeException());

        // When - Then
        assertThrows(RuntimeException.class, () -> tourneeComponent.createTournee(tourneeEntity));
    }

}
