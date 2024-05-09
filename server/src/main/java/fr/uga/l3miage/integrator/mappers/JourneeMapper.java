package fr.uga.l3miage.integrator.mappers;


import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface JourneeMapper {



    @Mapping(source = "reference", target = "reference")
    @Mapping(source = "etat", target = "etat")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "distanceAParcourir", target = "distanceAParcourir")
    @Mapping(source = "montant", target = "montant")
    @Mapping(source = "tdmTheorique", target = "tdmTheorique")
    JourneeEntity toEntity(JourneeResponseDTO journeeResponseDTO);

    @Mapping(source = "reference", target = "reference")
    @Mapping(source = "etat", target = "etat")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "distanceAParcourir", target = "distanceAParcourir")
    @Mapping(source = "montant", target = "montant")
    @Mapping(source = "tdmTheorique", target = "tdmTheorique")
    JourneeResponseDTO toResponse(JourneeEntity journeeEntity);




}
