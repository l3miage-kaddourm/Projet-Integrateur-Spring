package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.models.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testRequestfindByEmail(){
        //given
        ClientEntity clientEntity = ClientEntity
                .builder()
                .prenom("Client1")
                .email("test1@gmail.com")
                .build();

        ClientEntity clientEntity1 = ClientEntity
                .builder()
                .prenom("Client2")
                .email("test2@gmail.com")
                .build();

        clientRepository.save(clientEntity);
        clientRepository.save(clientEntity1);

        //when

        ClientEntity clientEntityResponse = clientRepository.findByEmail("test2@gmail.com");

        //then
        assertThat(clientEntityResponse.getPrenom()).isEqualTo("Client2");
    }
}
