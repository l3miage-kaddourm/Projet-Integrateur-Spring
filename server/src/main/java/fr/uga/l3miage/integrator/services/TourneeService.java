package fr.uga.l3miage.integrator.services;


import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.requests.TourneeCreateRequest;
import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourneeService {

    private final TourneeComponent tourneeComponent;
    private final TourneeMapper tourneeMapper;

    public TourneeResponseDTO createTournee(TourneeCreateRequest tourneeCreateRequest) {
        try {
            TourneeEntity tourneeEntity = tourneeMapper.toEntity(tourneeCreateRequest);
            TourneeEntity createdTourneeEntity = tourneeComponent.createTournee(tourneeEntity);
            return tourneeMapper.toResponse(createdTourneeEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating tournee", e);
        }
    }
}
