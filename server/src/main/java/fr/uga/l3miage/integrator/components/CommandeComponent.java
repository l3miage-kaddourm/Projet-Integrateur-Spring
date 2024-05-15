package fr.uga.l3miage.integrator.components;


import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCommandsException;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandeComponent {

    private final CommandeRepository commandeRepository;

    public Set<CommandeEntity> findAllcommandsouverte() throws NotFoundCommandsException {
        Set<CommandeEntity> commandes = commandeRepository.findAllByEtat(EtatsDeCommande.ouverte);
        if(commandes.isEmpty())
            throw new NotFoundCommandsException("Aucune Commande Trouvée");;
        return commandes;
    }

    public List<CommandeEntity> findAllCommandes() throws NotFoundCommandsException {
        List<CommandeEntity> commandes = commandeRepository.findAll();
        if(commandes.isEmpty())
            throw new NotFoundCommandsException("Aucune Commande Trouvée");
        return commandes;

    }

}
