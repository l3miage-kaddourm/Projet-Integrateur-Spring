package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TourneeComponent {

    private final TourneeRepository tourneeRepository;

    public TourneeEntity createTournee(TourneeEntity tourneeEntity) {

       try {
           return tourneeRepository.save(tourneeEntity);
       } catch (Exception e) {
           throw new RuntimeException("Error while creating tournee", e);
       }
    }
}
