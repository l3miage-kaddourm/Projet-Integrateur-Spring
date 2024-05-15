package fr.uga.l3miage.integrator.mappers;//package fr.uga.l3miage.integrator.mappers;
//
//import fr.uga.l3miage.integrator.models.EntrepotEntity;
//import fr.uga.l3miage.integrator.models.JourneeEntity;
//import fr.uga.l3miage.integrator.models.TourneeEntity;
//import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
//import fr.uga.l3miage.integrator.repositories.TourneeRepository;
//import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
//import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
//import fr.uga.l3miage.integrator.responses.TourneeResponseDTO;
//import lombok.Getter;
//import lombok.Setter;
//import org.mapstruct.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Set;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//@Getter
//@Setter
//@Primary
//@Component
//public abstract class JourneeMapperDecorator implements JourneeMapper {
//
//    @Autowired
//    private EntrepotRepository entrepotRepository;
//
//    @Autowired
//    private JourneeMapper journeeMapper;
//
//    @Autowired
//    private TourneeMapper tourneeMapper;
//
//    @Autowired
//    private TourneeRepository tourneeRepository;
//
//    @Override
//    public JourneeEntity toJourneeEntity(JourneeCreateRequest request) {
//        JourneeEntity journeeEntity = journeeMapper.toJourneeEntity(request);
//
//        Set<TourneeEntity> tourneeEntities = request.getTournees().stream()
//                .map(tourneeRequest -> tourneeRepository.findById(tourneeRequest.getReference())
//                        .orElseGet(() -> tourneeMapper.toEntityFromJournee(tourneeRequest)))
//                .collect(Collectors.toSet());
//        journeeEntity.setTournees(tourneeEntities);
//
//        EntrepotEntity entrepotEntity = entrepotRepository.findByNom(request.getEntrepotName());
//        if (entrepotEntity != null) {
//            journeeEntity.setEntrepot(entrepotEntity);
//            System.out.println("EntrepotEntity details: " + entrepotEntity);
//        } else {
//            System.out.println("No EntrepotEntity found with name: " + request.getEntrepotName());
//        }
//
//        for (TourneeEntity tourneeEntity : tourneeEntities) {
//            tourneeEntity.setJournee(journeeEntity);
//        }
//
//        return journeeEntity;
//    }
//
//    @Override
//    public JourneeResponseDTO toResponse(JourneeEntity journeeEntity) {
//        JourneeResponseDTO journeeResponseDTO = journeeMapper.toResponse(journeeEntity);
//
//        if (journeeEntity.getTournees() != null) {
//            Set<TourneeResponseDTO> tourneeResponseDTOs = journeeEntity.getTournees().stream()
//                    .map(tourneeMapper::toResponse)
//                    .collect(Collectors.toSet());
//            journeeResponseDTO.setTournees(tourneeResponseDTOs);
//        }
//
//        return journeeResponseDTO;
//    }
//}