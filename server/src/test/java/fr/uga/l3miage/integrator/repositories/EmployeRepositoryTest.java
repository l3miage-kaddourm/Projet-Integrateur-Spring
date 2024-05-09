package fr.uga.l3miage.integrator.repositories;


import fr.uga.l3miage.integrator.enums.Emploi;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class EmployeRepositoryTest {
    @Autowired
    private EmployeRepository employeRepository;

    @Test
    void testfindAllByEmploi() {
        //given
        EmployeEntity employeEntity = EmployeEntity
                .builder()
                .trigramme("FTG")
                .prenom("Employe1")
                .emploi(Emploi.livreur)
                .email("test1@gmail.com")
                .build();

        EmployeEntity employeEntity1 = EmployeEntity
                .builder()
                .trigramme("GHI")
                .emploi(Emploi.livreur)
                .prenom("Employe2")
                .email("test2@gmail.com")
                .build();

        EmployeEntity employeEntity2 = EmployeEntity
                .builder()
                .trigramme("FRE")
                .emploi(Emploi.planificateur)
                .prenom("Employe3")
                .email("test3@gmail.com")
                .build();

        employeRepository.save(employeEntity);
        employeRepository.save(employeEntity1);
        employeRepository.save(employeEntity2);

        //when

        Set<EmployeEntity> employeResponses = employeRepository.findAllByEmploi(Emploi.livreur);

        //then
        assertThat(employeResponses).hasSize(15);
    }
}
