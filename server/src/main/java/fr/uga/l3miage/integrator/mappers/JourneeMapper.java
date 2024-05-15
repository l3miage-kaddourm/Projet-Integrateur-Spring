package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.JourneeUpdateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {TourneeMapper.class, EntrepotMapper.class})
public interface JourneeMapper {


    JourneeResponseDTO toResponse(JourneeEntity journeeEntity);

    JourneeEntity toEntityFromResponse(JourneeResponseDTO journeeResponseDTO);


    JourneeEntity toJourneeEntityFromRequest(JourneeCreateRequest request);

    JourneeEntity toResponseFromUpdateRequest(JourneeUpdateRequest request);
}
