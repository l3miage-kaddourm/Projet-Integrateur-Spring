package fr.uga.l3miage.integrator.components;


import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivraisonComponent {
    final LivraisonRepository livraisonRepository;

}
