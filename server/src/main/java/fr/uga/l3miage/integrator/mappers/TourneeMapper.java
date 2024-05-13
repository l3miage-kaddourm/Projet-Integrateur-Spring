package fr.uga.l3miage.integrator.mappers;


import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.TourneeFromJourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TourneeMapper {


        TourneeEntity toEntity(TourneeCreateRequest tourneeCreateRequest);

        TourneeResponseDTO toResponse(TourneeEntity tourneeEntity);

        TourneeEntity toEntityFromJournee(TourneeFromJourneeCreateRequest tourneeFromJourneeCreateRequest);

}
