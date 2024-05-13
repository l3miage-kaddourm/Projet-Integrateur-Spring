package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface JourneeMapper {


    JourneeResponseDTO toResponse(JourneeEntity journeeEntity);

    JourneeEntity toJourneeEntityFromTournee(JourneeResponseDTO journeeResponseDTO);


    JourneeEntity toJourneeEntity(JourneeCreateRequest request);
}
