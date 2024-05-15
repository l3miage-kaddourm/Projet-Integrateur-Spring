package fr.uga.l3miage.integrator.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fr.uga.l3miage.integrator.CsvStrategies.CommandeStrategie;
import fr.uga.l3miage.integrator.components.CommandeComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundCommandsRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEmployeRestException;
import fr.uga.l3miage.integrator.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.repositories.ClientRepository;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.requests.CommandeCreateRequest;
import fr.uga.l3miage.integrator.responses.CommandeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ClientRepository clientRepository;

    @Autowired
    private final CommandeRepository commandeRepository;

    @Autowired
    private final CommandeMapper commandeMapper;

    @Autowired
    private CommandeComponent commandeComponent;

    @Autowired
    private LivraisonRepository livraisonRepository;

    public Set<CommandeResponseDTO> getAllCommandsOuvertes() {
        try {
            return commandeComponent.findAllcommandsouverte().stream()
                    .map(commandeMapper::toCommandeResponseDTO)
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new NotFoundCommandsRestException(e.getMessage(), NotFoundCommandsRestException.Type.NOTFOUND);
        }
    }

    public Set<CommandeResponseDTO> getAllCommands() {
        try {
            return commandeComponent.findAllCommandes().stream()
                    .map(commandeMapper::toCommandeResponseDTO)
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new NotFoundEmployeRestException(e.getMessage(), NotFoundEmployeRestException.Type.NOTFOUND);
        }
    }

    public void importCsv() throws IOException {
        Set<CommandeEntity> commandes = parseCsv();
        commandeRepository.saveAll(commandes);
    }

    public Set<CommandeEntity> parseCsv() throws IOException {
        try (Reader reader = new BufferedReader(new FileReader("C:\\Users\\Mohamed AEK\\Desktop\\Projet-Integrateur-Springc copy\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Commandes.csv"))) {
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
                        ClientEntity clientEntity = clientRepository.findByEmail(csvLine.getClient());
                        LocalDateTime dateDeCreation = LocalDateTime.parse(csvLine.getDateDeCreation(), formatter);
                        return CommandeEntity.builder()
                                .reference(csvLine.getReference())
                                .dateDeCreation(dateDeCreation)
                                .note(csvLine.getNote())
                                .etat(csvLine.getEtat())
                                .commentaire(csvLine.getCommentaire())
                                .client(clientEntity)
                                .build();
                    })
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new CsvImportRestException("Error during the importation of commands", e);
        }
    }

    public void updateCommandeEntity(CommandeEntity commandeEntity, CommandeCreateRequest commandeRequest) {
        commandeEntity.setEtat(commandeRequest.getEtat());
        commandeEntity.setDateDeCreation(commandeRequest.getDateDeCreation());
        commandeEntity.setNote(commandeRequest.getNote());
        commandeEntity.setCommentaire(commandeRequest.getCommentaire());
        commandeEntity.setMontant(commandeRequest.getMontant());
        commandeEntity.setTddTheorique(commandeRequest.getTddTheorique());
        commandeEntity.setTdmTheorique(commandeRequest.getTdmTheorique());
        commandeEntity.setDateDeLivraisonEffective(commandeRequest.getDateDeLivraisonEffective());
        commandeEntity.setDureeDeLivraison(commandeRequest.getDureeDeLivraison());
    }
}
