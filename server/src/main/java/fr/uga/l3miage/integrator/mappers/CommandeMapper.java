package fr.uga.l3miage.integrator.mappers;



import fr.uga.l3miage.integrator.models.CommandeEntity;

import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import org.mapstruct.Mapper;


@Mapper
public interface CommandeMapper {



    CommandeResponseDTO toCommandeResponseDTO(CommandeEntity commandeEntity);


}
