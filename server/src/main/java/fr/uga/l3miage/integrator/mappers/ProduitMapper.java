package fr.uga.l3miage.integrator.mappers;


import fr.uga.l3miage.integrator.models.ProduitEntity;
import fr.uga.l3miage.integrator.responses.ProduitResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProduitMapper {

    ProduitResponseDTO toProduitResponseDTO(ProduitEntity produitEntity);
}
