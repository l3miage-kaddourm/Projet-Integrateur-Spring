package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.JourneeUpdateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class JourneeMapperTest {

    @InjectMocks
    private JourneeMapper journeeMapper = Mappers.getMapper(JourneeMapper.class);

    @Mock
    private EntrepotMapper entrepotMapper;

    @Mock
    private TourneeMapper tourneeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldMapJourneeCreateRequestToJourneeEntity() {
        // Given
        JourneeCreateRequest request = new JourneeCreateRequest();
        request.setReference("JOURNEE-1");
        request.setEtat(EtatsDeJournee.enCours);
        request.setDate(new Date());
        request.setDistanceAParcourir(100.0);
        request.setMontant(200.0);

        // When
        JourneeEntity journeeEntity = journeeMapper.toJourneeEntityFromRequest(request);

        // Then
        assertNotNull(journeeEntity);
        assertEquals(request.getReference(), journeeEntity.getReference());
        assertEquals(request.getEtat(), journeeEntity.getEtat());
        assertEquals(request.getDate(), journeeEntity.getDate());
        assertEquals(request.getDistanceAParcourir(), journeeEntity.getDistanceAParcourir());
        assertEquals(request.getMontant(), journeeEntity.getMontant());
    }

    @Test
    void shouldMapJourneeEntityToJourneeResponseDTO() {
        // Given
        JourneeEntity journeeEntity = new JourneeEntity();
        journeeEntity.setReference("JOURNEE-1");
        journeeEntity.setEtat(EtatsDeJournee.enCours);
        journeeEntity.setDate(new Date());
        journeeEntity.setDistanceAParcourir(100.0);
        journeeEntity.setMontant(200.0);

        // When
        JourneeResponseDTO responseDTO = journeeMapper.toResponse(journeeEntity);

        // Then
        assertNotNull(responseDTO);
        assertEquals(journeeEntity.getReference(), responseDTO.getReference());
        assertEquals(journeeEntity.getEtat(), responseDTO.getEtat());
        assertEquals(journeeEntity.getDate(), responseDTO.getDate());
        assertEquals(journeeEntity.getDistanceAParcourir(), responseDTO.getDistanceAParcourir());
        assertEquals(journeeEntity.getMontant(), responseDTO.getMontant());
    }

    @Test
    void shouldMapJourneeResponseDTOToJourneeEntityFromResponse() {
        // Given
        JourneeResponseDTO responseDTO = new JourneeResponseDTO();
        responseDTO.setReference("JOURNEE-1");
        responseDTO.setEtat(EtatsDeJournee.enCours);
        responseDTO.setDate(new Date());
        responseDTO.setDistanceAParcourir(100.0);
        responseDTO.setMontant(200.0);

        // When
        JourneeEntity journeeEntity = journeeMapper.toEntityFromResponse(responseDTO);

        // Then
        assertNotNull(journeeEntity);
        assertEquals(responseDTO.getReference(), journeeEntity.getReference());
        assertEquals(responseDTO.getEtat(), journeeEntity.getEtat());
        assertEquals(responseDTO.getDate(), journeeEntity.getDate());
        assertEquals(responseDTO.getDistanceAParcourir(), journeeEntity.getDistanceAParcourir());
        assertEquals(responseDTO.getMontant(), journeeEntity.getMontant());
    }

}