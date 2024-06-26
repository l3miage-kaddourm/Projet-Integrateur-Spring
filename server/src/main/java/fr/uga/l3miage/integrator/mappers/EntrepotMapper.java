package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.responses.EntrepotResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface EntrepotMapper {


    EntrepotResponseDTO entrepotToEntrepotDTO(EntrepotEntity entrepot);

    EntrepotEntity entrepotDTOToEntrepot(EntrepotResponseDTO entrepot);
}
