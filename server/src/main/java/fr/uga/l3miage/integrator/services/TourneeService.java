package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.mappers.*;
import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.repositories.*;
import fr.uga.l3miage.integrator.requests.*;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
@RequiredArgsConstructor
public class TourneeService {

    private final TourneeRepository tourneeRepository;
    private final JourneeRepository journeeRepository;
    private final EntrepotRepository entrepotRepository;
    private final LivraisonRepository livraisonRepository;
    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final TourneeMapper tourneeMapper;
    private final JourneeMapper journeeMapper;
    private final EntrepotMapper entrepotMapper;
    private final LivraisonMapper livraisonMapper;
    private final CommandeMapper commandeMapper;
    private final ClientMapper clientMapper;
    private final CommandeService commandeService;

    @Transactional
    public TourneeResponseDTO createTournee(TourneeCreateRequest request) {
        try {
            // Check if the EntrepotEntity exists
            EntrepotEntity entrepotEntity = null;
            if (request.getJournee() != null && request.getJournee().getEntrepot() != null) {
                entrepotEntity = entrepotRepository.findById(request.getJournee().getEntrepot().getNom()).orElse(null);
            }

            // If the EntrepotEntity does not exist, throw an error
            if (entrepotEntity == null && request.getJournee() != null && request.getJournee().getEntrepot() != null) {
                throw new RuntimeException("Entrepot not found: " + request.getJournee().getEntrepot().getNom());
            }

            final EntrepotEntity finalEntrepotEntity = entrepotEntity;

            // Check if the JourneeEntity exists and create it if it does not
            JourneeEntity journeeEntity = null;
            if (request.getJournee() != null) {
                journeeEntity = journeeRepository.findById(request.getJournee().getReference())
                        .orElseGet(() -> {
                            JourneeEntity newJournee = journeeMapper.toJourneeEntityFromRequest(request.getJournee());
                            newJournee.setEntrepot(finalEntrepotEntity);
                            return journeeRepository.save(newJournee);
                        });
            }

            // Check if the TourneeEntity exists and update it, or create a new one
            Optional<TourneeEntity> optionalTourneeEntity = tourneeRepository.findById(request.getReference());
            TourneeEntity tourneeEntity;
            if (optionalTourneeEntity.isPresent()) {
                tourneeEntity = optionalTourneeEntity.get();
                // Update the existing tourneeEntity with the new details
                tourneeMapper.updateEntity(tourneeEntity, request);
            } else {
                tourneeEntity = tourneeMapper.toEntity(request);
                tourneeEntity.setJournee(journeeEntity);
            }

            // Save the TourneeEntity in the database first
            tourneeEntity = tourneeRepository.save(tourneeEntity);

            // Handle livraisons in the tournee
            if (request.getLivraisons() != null) {
                for (LivraisonCreateRequest livraisonRequest : request.getLivraisons()) {
                    LivraisonEntity livraisonEntity = livraisonRepository.findById(livraisonRequest.getReference()).orElse(null);

                    // If the LivraisonEntity does not exist, create it
                    if (livraisonEntity == null) {
                        livraisonEntity = livraisonMapper.toEntity(livraisonRequest);
                        livraisonEntity.setTournee(tourneeEntity);
                    }

                    // Handle commandes in the livraison
                    if (livraisonRequest.getCommandes() != null) {
                        livraisonEntity.getCommandes().clear();
                        for (CommandeCreateRequest commandeRequest : livraisonRequest.getCommandes()) {
                            CommandeEntity commandeEntity = commandeRepository.findById(commandeRequest.getReference()).orElse(null);

                            if (commandeEntity == null) {
                                // If the CommandeEntity does not exist, create it
                                commandeEntity = commandeMapper.toEntity(commandeRequest);
                            } else {
                                // If the CommandeEntity exists, update it with new information
                                commandeService.updateCommandeEntity(commandeEntity, commandeRequest);
                            }

                            // Handle client in the commande
                            if (commandeRequest.getClient() != null) {
                                Optional<ClientEntity> optionalClient = clientRepository.findById(commandeRequest.getClient().getEmail());
                                if (optionalClient.isPresent()) {
                                    commandeEntity.setClient(optionalClient.get());
                                } else {
                                    throw new RuntimeException("Client not found: " + commandeRequest.getClient().getEmail());
                                }
                            }

                            // Set the livraison reference in the commande
                            commandeEntity.setLivraison(livraisonEntity);

                            livraisonEntity.getCommandes().add(commandeEntity);
                            commandeRepository.save(commandeEntity);
                        }
                    }

                    livraisonRepository.save(livraisonEntity);
                    tourneeEntity.addLivraison(livraisonEntity);
                }
            }

            // Update and save the JourneeEntity
            if (journeeEntity != null) {
                journeeRepository.save(journeeEntity);
            }

            // Return a TourneeResponseDTO created from the saved TourneeEntity
            return tourneeMapper.toResponseDTO(tourneeEntity);
        } catch (Exception e) {
            LOGGER.error("Error while creating tournee", e);
            throw new RuntimeException("Error while creating tournee", e);
        }
    }


}
