package fr.uga.l3miage.integrator.components;



import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundLivreursRestException;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCommandsException;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LigneEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Optional;
import java.util.Set;
import java.util.List;


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

    public Set<LigneEntity> findLignesByCommande(String reference) throws NotFoundCommandsException {
        Optional<CommandeEntity> commandeOpt = commandeRepository.findByReference(reference);
        if(commandeOpt.isEmpty()) {
            throw new NotFoundCommandsException("Aucune Commande Trouvée");
        }
        CommandeEntity commande = commandeOpt.get();
        return commande.getLignes();
    }
}
