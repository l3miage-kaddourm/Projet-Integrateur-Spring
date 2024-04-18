package fr.uga.l3miage.integrator.services;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.repositories.EmployeRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class CsvService {

    private final EmployeRepository employeRepository;

    public CsvService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public void saveCsvToDatabase(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            CsvToBean<EmployeEntity> csvToBean = new CsvToBeanBuilder<EmployeEntity>(reader)
                    .withType(EmployeEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EmployeEntity> employes = csvToBean.parse();

            employeRepository.saveAll(employes);
        } catch (IOException e) {
            // handle exceptions
        }
    }
}