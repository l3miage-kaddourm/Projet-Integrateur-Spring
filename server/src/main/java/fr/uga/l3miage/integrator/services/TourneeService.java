package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
@RequiredArgsConstructor
public class TourneeService {

    private final TourneeRepository tourneeRepository;
    private final TourneeMapper tourneeMapper;
    private final JourneeRepository journeeRepository;
    private final JourneeMapper journeeMapper;




    public TourneeResponseDTO createTournee(TourneeCreateRequest request) {
        try {
            JourneeEntity journeeEntity = journeeRepository.findById(request.getJournee().getReference())
                    .orElseGet(() -> {
                        JourneeEntity newJournee = journeeMapper.toJourneeEntityFromTournee(request.getJournee());
                        return journeeRepository.save(newJournee);
                    });

            TourneeEntity tourneeEntity = tourneeMapper.toEntity(request);

            tourneeRepository.save(tourneeEntity);
            journeeRepository.save(journeeEntity); // Save the JourneeEntity after adding the TourneeEntity

            return tourneeMapper.toResponse(tourneeEntity);
        } catch (Exception e) {
            LOGGER.error("Error while creating tournee", e);
            throw new RuntimeException("Error while creating tournee", e);
        }
    }
}

