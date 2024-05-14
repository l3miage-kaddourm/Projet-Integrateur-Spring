package fr.uga.l3miage.integrator.mappers;



import fr.uga.l3miage.integrator.models.CommandeEntity;

import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring", uses = LigneMapper.class)
public interface CommandeMapper {

    @Mapping(source = "lignes", target = "lignesProduits")
    CommandeResponseDTO toCommandeResponseDTO(CommandeEntity commandeEntity);

    Set<CommandeResponseDTO> toCommandeResponseDTOs(List<CommandeEntity> commandeEntities);
}
