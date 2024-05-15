//package fr.uga.l3miage.integrator.services;
//
//
//import fr.uga.l3miage.integrator.components.JourneeComponent;
//import fr.uga.l3miage.integrator.exceptions.rest.NotFoundJourneeRestException;
//import fr.uga.l3miage.integrator.exceptions.technical.NotFoundJourneeException;
//import fr.uga.l3miage.integrator.mappers.EntrepotMapper;
//import fr.uga.l3miage.integrator.mappers.JourneeMapper;
//import fr.uga.l3miage.integrator.mappers.LivraisonMapper;
//import fr.uga.l3miage.integrator.mappers.TourneeMapper;
//import fr.uga.l3miage.integrator.models.EntrepotEntity;
//import fr.uga.l3miage.integrator.models.JourneeEntity;
//import fr.uga.l3miage.integrator.models.LivraisonEntity;
//import fr.uga.l3miage.integrator.models.TourneeEntity;
//import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
//import fr.uga.l3miage.integrator.repositories.JourneeRepository;
//import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
//import fr.uga.l3miage.integrator.repositories.TourneeRepository;
//import fr.uga.l3miage.integrator.requests.JourneeCreateRequest;
//import fr.uga.l3miage.integrator.requests.JourneeUpdateRequest;
//import fr.uga.l3miage.integrator.requests.LivraisonCreateRequest;
//import fr.uga.l3miage.integrator.requests.TourneeFromJourneeCreateRequest;
//import fr.uga.l3miage.integrator.responses.EntrepotResponseDTO;
//import fr.uga.l3miage.integrator.responses.JourneeResponseDTO;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureTestDatabase
//public class JourneeServiceTest {
//
//    @Mock
//    private JourneeRepository journeeRepository;
//
//    @Mock
//    private TourneeRepository tourneeRepository;
//
//    @Mock
//    private EntrepotRepository entrepotRepository;
//
//    @Mock
//    private EntrepotMapper entrepotMapper;
//
//    @Mock
//    private JourneeMapper journeeMapper;
//
//    @Mock
//    private TourneeMapper tourneeMapper;
//
//    @Mock
//    private LivraisonMapper livraisonMapper;
//
//    @Mock
//    private LivraisonRepository livraisonRepository;
//
//    @Mock
//    private JourneeComponent journeeComponent;
//
//    @InjectMocks
//    private JourneeService journeeService;
//
//    @Test
//    public void createJournee_ShouldCreateAndReturnJourneeResponseDTO() {
//        // Given
//        JourneeCreateRequest request = new JourneeCreateRequest();
//        request.setEntrepot(EntrepotResponseDTO.builder().nom("Entrepot1").build());
//        TourneeFromJourneeCreateRequest tourneeRequest = new TourneeFromJourneeCreateRequest();
//        tourneeRequest.setReference("Tournee1");
//        LivraisonCreateRequest livraisonRequest = new LivraisonCreateRequest();
//        livraisonRequest.setReference("Livraison1");
//        tourneeRequest.setLivraisons(Set.of(livraisonRequest));
//        request.setTournees(Set.of(tourneeRequest));
//
//        EntrepotEntity entrepotEntity = EntrepotEntity.builder().nom("Entrepot1").build();
//        JourneeEntity journeeEntity = JourneeEntity.builder().build();
//        TourneeEntity tourneeEntity = TourneeEntity.builder().build();
//        LivraisonEntity livraisonEntity = LivraisonEntity.builder().build();
//
//        when(entrepotRepository.findById(anyString())).thenReturn(Optional.of(entrepotEntity));
//        when(entrepotRepository.save(any(EntrepotEntity.class))).thenReturn(entrepotEntity);
//        when(journeeMapper.toJourneeEntityFromRequest(any(JourneeCreateRequest.class))).thenReturn(journeeEntity);
//        when(journeeRepository.save(any(JourneeEntity.class))).thenReturn(journeeEntity);
//        when(tourneeRepository.findById(anyString())).thenReturn(Optional.empty());
//        when(tourneeMapper.toEntityFromJournee(any(TourneeFromJourneeCreateRequest.class))).thenReturn(tourneeEntity);
//        when(livraisonRepository.findById(anyString())).thenReturn(Optional.empty());
//        when(livraisonMapper.toEntity(any(LivraisonCreateRequest.class))).thenReturn(livraisonEntity);
//        when(journeeMapper.toResponse(any(JourneeEntity.class))).thenReturn(new JourneeResponseDTO());
//
//        // When
//        JourneeResponseDTO result = journeeService.createJournee(request);
//
//        // Then
//        assertNotNull(result);
//        verify(journeeRepository, times(2)).save(any(JourneeEntity.class));
//        verify(tourneeRepository, times(1)).save(any(TourneeEntity.class));
//        verify(livraisonRepository, times(1)).save(any(LivraisonEntity.class));
//    }
//
//    @Test
//    public void updateJournee_ShouldUpdateJournee_WhenJourneeExists() throws NotFoundJourneeException {
//        // Given
//        String journeeRef = "Journee1";
//        JourneeUpdateRequest journeeUpdateRequest = new JourneeUpdateRequest();
//        JourneeEntity journeeEntity = new JourneeEntity();
//        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Journée mise à jour avec succès");
//
//        when(journeeComponent.updateJournee(anyString(), any(JourneeEntity.class))).thenReturn(expectedResponse);
//
//        // When
//        ResponseEntity<String> result = journeeService.updateJournee(journeeRef, journeeUpdateRequest);
//
//        // Then
//        assertEquals(expectedResponse, result);
//    }
//
//    @Test
//    public void updateJournee_ShouldThrowNotFoundJourneeRestException_WhenJourneeDoesNotExist() throws NotFoundJourneeException {
//        // Given
//        String journeeRef = "Journee1";
//        JourneeUpdateRequest journeeUpdateRequest = new JourneeUpdateRequest();
//
//        when(journeeComponent.updateJournee(anyString(), any(JourneeEntity.class)))
//                .thenThrow(new NotFoundJourneeException("référence de journée ne correspond à aucune journée existante"));
//
//        // When & Then
//        NotFoundJourneeRestException exception = assertThrows(NotFoundJourneeRestException.class,
//                () -> journeeService.updateJournee(journeeRef, journeeUpdateRequest));
//
//        assertEquals("référence de journée ne correspond à aucune journée existante", exception.getMessage());
//    }
//}
