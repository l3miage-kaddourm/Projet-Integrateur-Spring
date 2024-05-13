package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.CamionEntity;
import fr.uga.l3miage.integrator.responses.CamionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CamionMapper {

      CamionMapper INSTANCE = Mappers.getMapper(CamionMapper.class);

      CamionResponseDTO camionToCamionDTO(CamionEntity camion);
}
