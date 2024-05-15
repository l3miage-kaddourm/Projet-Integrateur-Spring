package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.requests.CommandeCreateRequest;
import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    @Mapping(source = "client.email", target = "client.email")
    CommandeEntity toEntity(CommandeCreateRequest request);

    CommandeResponseDTO toCommandeResponseDTO(CommandeEntity commandeEntity);
}
