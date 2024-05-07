package fr.uga.l3miage.integrator.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fr.uga.l3miage.integrator.CsvStrategies.CommandeStrategie;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundLivreursRestException;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundLivreursException;
import fr.uga.l3miage.integrator.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.repositories.ClientRepository;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CommandeService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private final CommandeRepository commandeRepository;

    @Autowired
    private final CommandeMapper commandeMapper;


    public Set<CommandeResponseDTO> getAllCommands() {
            return commandeRepository.findAllBy().stream()
                    .map(commandeMapper::toCommandeResponseDTO)
                    .collect(Collectors.toSet());
    }




    @DependsOn("clientService.importCsv")
    public void importCsv() throws IOException{
        Set<CommandeEntity> commandes = parseCsv();
        commandeRepository.saveAll(commandes);

    }


    public Set<CommandeEntity> parseCsv() throws IOException {
        try (Reader reader = new BufferedReader(new FileReader("C:\\Users\\kanikisenci\\Desktop\\miage\\Projet_Integrateur\\Spring\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Commandes.csv"))) {
            HeaderColumnNameMappingStrategy<CommandeStrategie> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(CommandeStrategie.class);
            CsvToBean<CommandeStrategie> csvToBean =
                    new CsvToBeanBuilder<CommandeStrategie>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");

            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> {
//                        LivraisonEntity livraisonEntity = livraisonRepository.findByReference(csvLine.getLivraison());
                        ClientEntity clientEntity = clientRepository.findByEmail(csvLine.getClient());
                        LocalDateTime dateDeCreation = LocalDateTime.parse(csvLine.getDateDeCreation(), formatter);
                        return CommandeEntity.builder()
                                .reference(csvLine.getReference())
                                .dateDeCreation(dateDeCreation)
                                .note(csvLine.getNote())
                                .commentaire(csvLine.getCommentaire())
//                                .livraison(livraisonEntity)
                                .client(clientEntity)
                                .build();
                    })
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new CsvImportRestException("Error during the importation of commandes", e);
        }
    }
}
