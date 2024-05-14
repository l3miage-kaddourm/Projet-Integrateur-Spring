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
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
@RequiredArgsConstructor
public class TourneeService {

    private final TourneeRepository tourneeRepository;
    private final JourneeRepository journeeRepository;
    private final EntrepotRepository entrepotRepository;
    private final TourneeMapper tourneeMapper;
    private final JourneeMapper journeeMapper;
    private final EntrepotMapper entrepotMapper;

    public TourneeResponseDTO createTournee(TourneeCreateRequest request) {
        try {
            EntrepotEntity entrepotEntity = null;
            if (request.getJournee() != null && request.getJournee().getEntrepot() != null) {
                entrepotEntity = entrepotRepository.findById(request.getJournee().getEntrepot().getNom()).orElse(null);
            }

            if (entrepotEntity == null && request.getJournee() != null && request.getJournee().getEntrepot() != null) {
                entrepotEntity = entrepotMapper.entrepotDTOToEntrepot(request.getJournee().getEntrepot());


                entrepotRepository.save(entrepotEntity);
            }

            final EntrepotEntity finalEntrepotEntity = entrepotEntity;

            JourneeEntity journeeEntity = null;
            if (request.getJournee() != null) {
                journeeEntity = journeeRepository.findById(request.getJournee().getReference())
                        .orElseGet(() -> {
                            JourneeEntity newJournee = journeeMapper.toJourneeEntityFromRequest(request.getJournee());
                            newJournee.setEntrepot(finalEntrepotEntity);
                            return journeeRepository.save(newJournee);
                        });
            }

            TourneeEntity tourneeEntity = tourneeMapper.toEntity(request);

            tourneeRepository.save(tourneeEntity);
            if (journeeEntity != null) {
                journeeRepository.save(journeeEntity);
            }

            return tourneeMapper.toResponseDTO(tourneeEntity);
        } catch (Exception e) {
            LOGGER.error("Error while creating tournee", e);
            throw new RuntimeException("Error while creating tournee", e);
        }
    }
}
