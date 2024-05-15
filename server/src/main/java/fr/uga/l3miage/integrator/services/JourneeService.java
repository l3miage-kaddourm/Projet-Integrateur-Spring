package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.JourneeComponent;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundJourneeRestException;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundJourneeException;
import fr.uga.l3miage.integrator.mappers.*;
import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.repositories.*;

import fr.uga.l3miage.integrator.requests.*;

import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class JourneeService {

    private final JourneeRepository journeeRepository;
    private final TourneeRepository tourneeRepository;
    private final JourneeMapper journeeMapper;
    private final EntrepotRepository entrepotRepository;
    private final EntrepotMapper entrepotMapper;
    private final TourneeMapper tourneeMapper;
    private final LivraisonMapper livraisonMapper;
    private final LivraisonRepository livraisonRepository;
    private final JourneeComponent journeeComponent;
    private final EmployeRepository employeRepository;
    private final EmployeMapper employeMapper;

    @Transactional
    public JourneeResponseDTO createJournee(JourneeCreateRequest request) {
        try {
            // Check if the EntrepotEntity exists
            EntrepotEntity entrepotEntity = null;
            if (request.getEntrepot() != null) {
                entrepotEntity = entrepotRepository.findById(request.getEntrepot().getNom()).orElse(null);
            }

            // If the EntrepotEntity does not exist, create it
            if (entrepotEntity == null && request.getEntrepot() != null) {
                entrepotEntity = entrepotMapper.entrepotDTOToEntrepot(request.getEntrepot());
                entrepotRepository.save(entrepotEntity);
            }

            // Create a new JourneeEntity from the request
            JourneeEntity journeeEntity = journeeMapper.toJourneeEntityFromRequest(request);
            journeeEntity.setEntrepot(entrepotEntity);

            // Save the JourneeEntity in the database
            journeeEntity = journeeRepository.save(journeeEntity);

            // For each TourneeFromJourneeCreateRequest in the request, check if the TourneeEntity exists and create it if it does not
            for (TourneeFromJourneeCreateRequest tourneeRequest : request.getTournees()) {
                TourneeEntity tourneeEntity = tourneeRepository.findById(tourneeRequest.getReference()).orElse(null);

                // If the TourneeEntity does not exist, create it
                if (tourneeEntity == null) {
                    tourneeEntity = tourneeMapper.toEntityFromJournee(tourneeRequest);
                }

                // Ensure the tournees set is initialized
                if (tourneeEntity.getLivraisons() == null) {
                    tourneeEntity.setLivraisons(new HashSet<>());
                }

                // For each LivraisonCreateRequest in the tourneeRequest, create a LivraisonEntity and add it to the TourneeEntity
                for (LivraisonCreateRequest livraisonRequest : tourneeRequest.getLivraisons()) {
                    LivraisonEntity livraisonEntity = livraisonRepository.findById(livraisonRequest.getReference()).orElse(null);

                    // If the LivraisonEntity does not exist, create it
                    if (livraisonEntity == null) {
                        livraisonEntity = livraisonMapper.toEntity(livraisonRequest);
                    }

                    livraisonEntity.setTournee(tourneeEntity);  // Ensure association with TourneeEntity
                    tourneeEntity.addLivraison(livraisonEntity);
                    livraisonRepository.save(livraisonEntity);
                }

                // Ensure the employes set is initialized
                if (tourneeEntity.getEmployes() == null) {
                    tourneeEntity.setEmployes(new HashSet<>());
                }

                // For each EmployeCreateRequest in the tourneeRequest, create an EmployeEntity and add it to the TourneeEntity
                for (EmployeCreateRequest employeRequest : tourneeRequest.getEmployes()) {
                    EmployeEntity employeEntity = employeRepository.findById(employeRequest.getTrigramme()).orElse(null);

                    // If the EmployeEntity does not exist, create it
                    if (employeEntity == null) {
                        employeEntity = employeMapper.toEntity(employeRequest);
                    }

                    employeEntity.setTournee(tourneeEntity);  // Ensure association with TourneeEntity
                    tourneeEntity.addEmploye(employeEntity);
                    employeRepository.save(employeEntity);
                }

                journeeEntity.addTournee(tourneeEntity); // Add the tournee to the journee and set the relationship
                tourneeRepository.save(tourneeEntity); // Save the TourneeEntity in the database
            }

            // Save the JourneeEntity again to update the relationship
            journeeEntity = journeeRepository.save(journeeEntity);

            // Return a JourneeResponseDTO created from the saved JourneeEntity
            return journeeMapper.toResponse(journeeEntity);
        } catch (Exception e) {
            LOGGER.error("Error while creating Journee", e);
            throw new RuntimeException("Error while creating Journee", e);
        }
    }

    public ResponseEntity<String> updateJournee(String journeeRef, JourneeUpdateRequest journeeUpdateRequest) {
        try {
            return journeeComponent.updateJournee(journeeRef, journeeMapper.toResponseFromUpdateRequest(journeeUpdateRequest));
        } catch (NotFoundJourneeException e) {
            throw new NotFoundJourneeRestException(e.getMessage(), NotFoundJourneeRestException.Type.NOTFOUND);
        }
    }
}
