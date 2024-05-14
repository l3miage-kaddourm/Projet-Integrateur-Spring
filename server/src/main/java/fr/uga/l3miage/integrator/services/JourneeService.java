package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.mappers.EntrepotMapper;
import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;

import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.TourneeFromJourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.EntrepotResponseDTO;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class JourneeService {

    private final JourneeRepository journeeRepository;
    private final TourneeRepository tourneeRepository;
    private final JourneeMapper journeeMapper;
    private final EntrepotRepository entrepotRepository;
    private final EntrepotMapper entrepotMapper;
    private final TourneeMapper tourneeMapper;

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
                    // No need to set journeeEntity here; addTournee will do that
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
}
