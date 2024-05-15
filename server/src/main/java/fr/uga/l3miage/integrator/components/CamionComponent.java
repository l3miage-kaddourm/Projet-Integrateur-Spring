package fr.uga.l3miage.integrator.components;


import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCamionException;
import fr.uga.l3miage.integrator.models.CamionEntity;
import fr.uga.l3miage.integrator.repositories.CamionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor

public class CamionComponent {
    private final CamionRepository camionRepository;

    public Set<CamionEntity> findAllCamions() throws NotFoundCamionException {
        Set<CamionEntity> camions = camionRepository.findAllBy();
        if(camions.isEmpty())
            throw new NotFoundCamionException("Aucun Camion Trouvé");
        return camions;
    }
}
