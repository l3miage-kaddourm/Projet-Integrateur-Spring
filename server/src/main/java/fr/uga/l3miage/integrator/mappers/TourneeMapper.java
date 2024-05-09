package fr.uga.l3miage.integrator.mappers;


import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = JourneeMapper.class)
public interface TourneeMapper {

        @Mapping(source = "journee", target = "journee")
        TourneeEntity toEntity(TourneeCreateRequest tourneeCreateRequest);

        @Mapping(source = "journee", target = "journee")
        TourneeResponseDTO toResponse(TourneeEntity tourneeEntity);

}
