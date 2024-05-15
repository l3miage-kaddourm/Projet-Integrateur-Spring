package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.TourneeFromJourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class TourneeMapperTest {
    @Autowired
    private final TourneeMapper tourneeMapper = Mappers.getMapper(TourneeMapper.class);

    @Test
    void shouldMapTourneeCreateRequestToTourneeEntity() {
        // Given
        TourneeCreateRequest request = new TourneeCreateRequest();
        request.setReference("TOURNEE-1");
        request.setEtat(EtatsDeTournee.enParcours);
        request.setLettre("A");
        request.setMontant(100.0);
        request.setDistanceAparcourir(50.0);

        // When
        TourneeEntity tourneeEntity = tourneeMapper.toEntity(request);

        // Then
        assertNotNull(tourneeEntity);
        assertEquals(request.getReference(), tourneeEntity.getReference());
        assertEquals(request.getEtat(), tourneeEntity.getEtat());
        assertEquals(request.getLettre(), tourneeEntity.getLettre());
        assertEquals(request.getMontant(), tourneeEntity.getMontant());
        assertEquals(request.getDistanceAparcourir(), tourneeEntity.getDistanceAparcourir());
    }

    @Test
    void shouldMapTourneeEntityToTourneeResponseDTO() {
        // Given
        TourneeEntity tourneeEntity = new TourneeEntity();
        tourneeEntity.setReference("TOURNEE-1");
        tourneeEntity.setEtat(EtatsDeTournee.enParcours);
        tourneeEntity.setLettre("A");
        tourneeEntity.setMontant(100.0);
        tourneeEntity.setDistanceAparcourir(50.0);

        // When
        TourneeResponseDTO responseDTO = tourneeMapper.toResponseDTO(tourneeEntity);

        // Then
        assertNotNull(responseDTO);
        assertEquals(tourneeEntity.getReference(), responseDTO.getReference());
        assertEquals(tourneeEntity.getEtat(), responseDTO.getEtat());
        assertEquals(tourneeEntity.getLettre(), responseDTO.getLettre());
        assertEquals(tourneeEntity.getMontant(), responseDTO.getMontant());
        assertEquals(tourneeEntity.getDistanceAparcourir(), responseDTO.getDistanceAparcourir());
    }

    @Test
    void shouldMapTourneeFromJourneeCreateRequestToTourneeEntityFromJournee() {
        // Given
        TourneeFromJourneeCreateRequest request = new TourneeFromJourneeCreateRequest();
        request.setReference("TOURNEE-1");
        request.setEtat(EtatsDeTournee.enParcours);
        request.setLettre("A");
        request.setMontant(100.0);
        request.setDistanceAparcourir(50.0);

        // When
        TourneeEntity tourneeEntity = tourneeMapper.toEntityFromJournee(request);

        // Then
        assertNotNull(tourneeEntity);
        assertEquals(request.getReference(), tourneeEntity.getReference());
        assertEquals(request.getEtat(), tourneeEntity.getEtat());
        assertEquals(request.getLettre(), tourneeEntity.getLettre());
        assertEquals(request.getMontant(), tourneeEntity.getMontant());
        assertEquals(request.getDistanceAparcourir(), tourneeEntity.getDistanceAparcourir());
    }
}