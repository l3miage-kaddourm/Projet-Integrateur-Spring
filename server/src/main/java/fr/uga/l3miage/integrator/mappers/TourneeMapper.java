package fr.uga.l3miage.integrator.mappers;


import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.TourneeFromJourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface TourneeMapper {


        TourneeEntity toEntity(TourneeCreateRequest tourneeCreateRequest);

        TourneeResponseDTO toResponseDTO(TourneeEntity tourneeEntity);
        @Mapping(target = "livraisons", source = "livraisons")
        @Mapping(target = "employes", source = "employes")
        TourneeEntity toEntityFromJournee(TourneeFromJourneeCreateRequest tourneeFromJourneeCreateRequest);


        void updateEntity(@MappingTarget TourneeEntity tourneeEntity, TourneeCreateRequest request);




}
