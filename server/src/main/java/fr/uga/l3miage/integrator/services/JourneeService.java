package fr.uga.l3miage.integrator.services;


import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;

import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
import fr.uga.l3miage.integrator.requests.TourneeFromJourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;


@Service
@RequiredArgsConstructor
public class JourneeService {

    private final JourneeRepository journeeRepository;
    private final TourneeRepository tourneeRepository;
    private final JourneeMapper journeeMapper;




    public JourneeResponseDTO createJournee(JourneeCreateRequest request) {
        // Convert the request to a JourneeEntity
        try {
            JourneeEntity newjourneeEntity = journeeMapper.toJourneeEntity(request);

            // Save all the TourneeEntity instances in newjourneeEntity
            tourneeRepository.saveAll(newjourneeEntity.getTournees());

            // Save the JourneeEntity
            journeeRepository.save(newjourneeEntity);

            // Convert the saved JourneeEntity to a JourneeResponseDTO and return it
            return journeeMapper.toResponse(newjourneeEntity);
        } catch (Exception e) {
            LOGGER.error("Error while creating Jounree", e);
           throw new RuntimeException("Error while creating Journee", e);
        }

}
}
