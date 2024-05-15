package fr.uga.l3miage.integrator.components;



import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundJourneeException;
import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.requests.JourneeUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JourneeComponent {
    private final JourneeRepository journeeRepository;
    private final JourneeMapper journeeMapper;
    private final LivraisonComponent livraisonComponent;
    private final LivraisonRepository livraisonRepository;
    private final TourneeRepository tourneeRepository;


    public JourneeEntity getJournee(String journeeRef) {
        return journeeRepository.findById(journeeRef).orElse(null);
    }

    public ResponseEntity<String> updateJournee(String refJournee, JourneeEntity journeeUpdated) throws NotFoundJourneeException {
        JourneeEntity journeeToUpdate = getJournee(refJournee);
        if (journeeToUpdate == null) {
            throw new NotFoundJourneeException("référence de journée ne correspond à aucune journée existante");
        } else {
            EtatsDeJournee etat = journeeUpdated.getEtat();
            journeeToUpdate.setEtat(etat);

            if (etat == EtatsDeJournee.enCours) {
                updateTourneesAndLivraisons(journeeToUpdate, EtatsDeTournee.enParcours, EtatsDeLivraison.enParcours);
            } else if (etat == EtatsDeJournee.effectuee) {
                updateTourneesAndLivraisons(journeeToUpdate, EtatsDeTournee.effectuee, EtatsDeLivraison.effectuee);
            }

            journeeRepository.save(journeeToUpdate);

            return new ResponseEntity<>("Journée mise à jour avec succès", HttpStatus.OK);
        }
    }

    private void updateTourneesAndLivraisons(JourneeEntity journee, EtatsDeTournee tourneeEtat, EtatsDeLivraison livraisonEtat) {
        for (TourneeEntity tournee : journee.getTournees()) {
            tournee.setEtat(tourneeEtat);
            for (LivraisonEntity livraison : tournee.getLivraisons()) {
                livraison.setEtat(livraisonEtat);
                livraisonRepository.save(livraison);
            }
            tourneeRepository.save(tournee);
        }
    }

}

