package fr.uga.l3miage.integrator.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fr.uga.l3miage.integrator.CsvStrategies.CommandeStrategie;
import fr.uga.l3miage.integrator.components.CommandeComponent;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundCommandsRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundLivreursRestException;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundCommandsException;
import fr.uga.l3miage.integrator.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.repositories.ClientRepository;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
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
    private final CommandeComponent commandeComponent;

    @Autowired
    private final CommandeMapper commandeMapper;


    public Set<CommandeResponseDTO> getAllCommands() {
        try {
            return commandeComponent.findAllCommandes().stream()
                    .map(commandeMapper::toCommandeResponseDTO)
                    .collect(Collectors.toSet());

           } catch (NotFoundCommandsException e) {
            throw new NotFoundCommandsRestException("Aucune Commande Trouvée", NotFoundCommandsRestException.Type.NOTFOUND);
        } catch (Exception e) {
            throw new RuntimeException("Unhandled exception occurred", e);
        }
    }



    public void importCsv() throws IOException{
        Set<CommandeEntity> commandes = parseCsv();
        commandeRepository.saveAll(commandes);
    }


    public Set<CommandeEntity> parseCsv(){
        try (Reader reader = new BufferedReader(new FileReader("C:\\Users\\Mohamed AEK\\Desktop\\perso\\Etudes\\L3 UGA\\S6\\Projet\\Projet-Integrateur-Spring\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Commandes.csv"))) {
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
            throw new CsvImportRestException("Error during the importation of commands", e);
        }
    }
}
