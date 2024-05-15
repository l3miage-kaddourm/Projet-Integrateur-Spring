package fr.uga.l3miage.integrator.mappers;


import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.requests.LivraisonCreateRequest;
import fr.uga.l3miage.integrator.responses.LivraisonResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivraisonMapper {

    LivraisonEntity toEntity(LivraisonCreateRequest request);

    LivraisonResponseDTO toDTO(LivraisonEntity entity);
}
