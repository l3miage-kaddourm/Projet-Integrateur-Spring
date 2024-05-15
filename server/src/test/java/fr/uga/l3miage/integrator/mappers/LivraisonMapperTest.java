package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.requests.LivraisonCreateRequest;
import fr.uga.l3miage.integrator.responses.LivraisonResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class LivraisonMapperTest {
    @Autowired
    private LivraisonMapper livraisonMapper = Mappers.getMapper(LivraisonMapper.class);

    @Test
    void shouldMapLivraisonCreateRequestToLivraisonEntity() {
        // Given
        LivraisonCreateRequest request = LivraisonCreateRequest.builder()
                .reference("LIV-001")
                .etat(EtatsDeLivraison.enParcours)
                .montant(100.0)
                .distanceAparcourir(50.0)
                .tdpAlAller(10)
                .tdpTheorique(20)
                .tdmTheorique(30)
                .heureDeLivraison(Time.valueOf("12:00:00"))
                .heureDeLivraisonEffective(Time.valueOf("12:30:00"))
                .tdmEffectif(15)
                .build();

        // When
        LivraisonEntity livraisonEntity = livraisonMapper.toEntity(request);

        // Then
        assertNotNull(livraisonEntity);
        assertEquals(request.getReference(), livraisonEntity.getReference());
        assertEquals(request.getEtat(), livraisonEntity.getEtat());
        assertEquals(request.getMontant(), livraisonEntity.getMontant());
        assertEquals(request.getDistanceAparcourir(), livraisonEntity.getDistanceAparcourir());
        assertEquals(request.getTdmTheorique(), livraisonEntity.getTdmTheorique());
        assertEquals(request.getHeureDeLivraison(), livraisonEntity.getHeureDeLivraison());
        assertEquals(request.getHeureDeLivraisonEffective(), livraisonEntity.getHeureDeLivraisonEffective());
        assertEquals(request.getTdmEffectif(), livraisonEntity.getTdmEffectif());
    }

    @Test
    void shouldMapLivraisonEntityToLivraisonResponseDTO() {
        // Given
        LivraisonEntity livraisonEntity = LivraisonEntity.builder()
                .reference("LIV-001")
                .etat(EtatsDeLivraison.enParcours)
                .montant(100.0)
                .distanceAparcourir(50.0)
                .tdmTheorique(30)
                .heureDeLivraison(Time.valueOf("12:00:00"))
                .heureDeLivraisonEffective(Time.valueOf("12:30:00"))
                .tdmEffectif(15)
                .build();

        // When
        LivraisonResponseDTO responseDTO = livraisonMapper.toDTO(livraisonEntity);

        // Then
        assertNotNull(responseDTO);
        assertEquals(livraisonEntity.getReference(), responseDTO.getReference());
        assertEquals(livraisonEntity.getEtat(), responseDTO.getEtat());
        assertEquals(livraisonEntity.getMontant(), responseDTO.getMontant());
        assertEquals(livraisonEntity.getDistanceAparcourir(), responseDTO.getDistanceAparcourir());
        assertEquals(livraisonEntity.getTdmTheorique(), responseDTO.getTdmTheorique());
        assertEquals(livraisonEntity.getHeureDeLivraison(), responseDTO.getHeureDeLivraison());
        assertEquals(livraisonEntity.getHeureDeLivraisonEffective(), responseDTO.getHeureDeLivraisonEffective());
        assertEquals(livraisonEntity.getTdmEffectif(), responseDTO.getTdmEffectif());
    }
}