package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.responses.ClientResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {

    ClientResponseDTO toClientResponseDTO(ClientEntity clientEntity);
}
