package fr.uga.l3miage.integrator.services;


import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivraisonService {

    @Autowired
    private final LivraisonRepository livraisonRepository;



}
