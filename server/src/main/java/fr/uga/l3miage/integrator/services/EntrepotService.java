package fr.uga.l3miage.integrator.services;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import fr.uga.l3miage.integrator.CsvStrategies.EntrepotStrategie;
import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;


import fr.uga.l3miage.integrator.mappers.EntrepotMapper;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.responses.EntrepotResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntrepotService {
    @Autowired
    private final EntrepotRepository entrepotRepository;



    public void importCsv() throws IOException {
        Set<EntrepotEntity> entrepots = parseCsv();
        entrepotRepository.saveAll(entrepots);

    }
    public Set<EntrepotEntity> parseCsv() throws IOException{
        try(Reader reader = new BufferedReader(new FileReader("C:\\Users\\Mohamed AEK\\Desktop\\perso\\Etudes\\L3 UGA\\S6\\Projet\\Projet-Integrateur-Spring\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Entrepôts.csv"))){
            HeaderColumnNameMappingStrategy<EntrepotStrategie> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(EntrepotStrategie.class);
            CsvToBean<EntrepotStrategie> csvToBean =
                    new CsvToBeanBuilder<EntrepotStrategie>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreLeadingWhiteSpace(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> {
                        Adresse adresse = new Adresse();
                        adresse.setAdresse(csvLine.getAdresse());
                        adresse.setCodePostal(csvLine.getCodePostal());
                        adresse.setVille(csvLine.getVille());

                        GeoPosition position = new GeoPosition();
                        position.setLaltitude(csvLine.getLatitude());
                        position.setLongitude(csvLine.getLongitude());

                        return EntrepotEntity.builder()
                                .nom(csvLine.getNom())
                                .lettre(csvLine.getLettre())
                                .photo(csvLine.getPhoto())
                                .adresse(adresse)
                                .position(position)
                                .build();
                    })
                    .collect(Collectors.toSet());
        }
        catch (IOException e){
            throw new CsvImportRestException("Error during the importation of Entrepots", e);
        }

    }


}
