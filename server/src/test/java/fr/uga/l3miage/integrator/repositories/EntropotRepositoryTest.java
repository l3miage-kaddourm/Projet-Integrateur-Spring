package fr.uga.l3miage.integrator.repositories;


import fr.uga.l3miage.integrator.models.EntrepotEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class EntropotRepositoryTest {
    @Autowired
    private EntrepotRepository entrepotRepository;

    @Test
    void testfindByNom(){
        //given
        EntrepotEntity entrepotEntity = EntrepotEntity
                .builder()
                .nom("Walier")
                .lettre("W")
                .build();

        EntrepotEntity entrepotEntity1 = EntrepotEntity
                .builder()
                .nom("Davis")
                .lettre("D")
                .build();

        entrepotRepository.save(entrepotEntity);
        entrepotRepository.save(entrepotEntity1);

        //When
        EntrepotEntity entrepotResponse = entrepotRepository.findByNom("Walier");

        //Then
        assertThat(entrepotResponse.getLettre()).isEqualTo("W");
    }
}
