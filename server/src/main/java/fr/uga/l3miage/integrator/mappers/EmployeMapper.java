package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.requests.EmployeCreateRequest;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeMapper {

    @Mapping(source = "entrepot.nom", target = "entrepot")
    EmployeResponseDTO convertToDTO(EmployeEntity employeEntity);

    EmployeEntity  toEntity (EmployeCreateRequest employeCreateRequest);


}