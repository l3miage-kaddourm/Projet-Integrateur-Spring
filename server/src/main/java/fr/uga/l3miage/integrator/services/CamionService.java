package fr.uga.l3miage.integrator.services;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fr.uga.l3miage.integrator.CsvStrategies.CamionStrategie;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundLivreursRestException;
import fr.uga.l3miage.integrator.mappers.CamionMapper;
import fr.uga.l3miage.integrator.models.CamionEntity;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.repositories.CamionRepository;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.responses.CamionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CamionService {


    private final EntrepotRepository entrepotRepository;
    private final CamionRepository camionRepositry;
    private final CamionMapper camionMapper;




    public Set<CamionResponseDTO> getAllCamions() {
        try {
            return camionRepositry.findAllBy().stream()
                    .map(camionMapper::camionToCamionDTO)
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            System.err.println("Error occurred while getting all camions: " + e.getMessage());
            return Collections.emptySet();
        }
    }



    public void importCsv() throws IOException {
        Set<CamionEntity> camions = parseCsv();
        camionRepositry.saveAll(camions);

    }


    public Set<CamionEntity> parseCsv() throws IOException {
        try (Reader reader = new BufferedReader(new FileReader("C:\\Users\\kanikisenci\\Desktop\\miage\\Projet_Integrateur\\Spring\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Camions.csv"))) {
            HeaderColumnNameMappingStrategy<CamionStrategie> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(CamionStrategie.class);
            CsvToBean<CamionStrategie> csvToBean =
                    new CsvToBeanBuilder<CamionStrategie>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> {
                        GeoPosition position = new GeoPosition();
                        position.setLaltitude(csvLine.getLaltitude());
                        position.setLongitude(csvLine.getLongitude());

                        EntrepotEntity entrepot = entrepotRepository.findByNom(csvLine.getEntrepot());
                        return CamionEntity.builder()
                                .immatriculation(csvLine.getImmatriculation())
                                .position(position)
                                .entrepot(entrepot)
                                .build();
                    })
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new CsvImportRestException("Error during the importation of Camions", e);
        }
    }
}
