package fr.uga.l3miage.integrator.services;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fr.uga.l3miage.integrator.CsvStrategies.ClientStrategie;
import fr.uga.l3miage.integrator.DataType.Adresse;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void importCsv() throws IOException {
        Set<ClientEntity> clients = parseCsv();
        clientRepository.saveAll(clients);

    }


    public Set<ClientEntity> parseCsv() throws IOException {
        try (Reader reader = new BufferedReader(new FileReader(new File("C:\\Users\\Mohamed AEK\\Desktop\\perso\\Etudes\\L3 UGA\\S6\\Projet\\Projet-Integrateur-Spring\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Clients.csv").getAbsolutePath()))) {
            HeaderColumnNameMappingStrategy<ClientStrategie> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(ClientStrategie.class);
            CsvToBean<ClientStrategie> csvToBean =
                    new CsvToBeanBuilder<ClientStrategie>(reader)
                            .withMappingStrategy(strategy)
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


                        return ClientEntity.builder()
                                .email(csvLine.getEmail())
                                .prenom(csvLine.getPrenom())
                                .nom(csvLine.getNom())
                                .adresse(adresse)
                                .position(position)
                                .etat(csvLine.getEtat())
                                .build();
                    })
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new CsvImportRestException("Error during the importation of commandes", e);
        }
    }
}
