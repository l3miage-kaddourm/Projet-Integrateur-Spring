package fr.uga.l3miage.integrator.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.CsvStrategies.LigneStrategie;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LigneEntity;
import fr.uga.l3miage.integrator.models.ProduitEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LigneRepository;
import fr.uga.l3miage.integrator.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LigneService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private LigneRepository ligneRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Transactional
    public void importCsv() throws IOException {
        Set<LigneEntity> LigneEntitys = parseCsv();
        ligneRepository.saveAll(LigneEntitys);
    }

    public Set<LigneEntity> parseCsv() throws IOException {
        try (Reader reader = new BufferedReader(new FileReader("C:\\Users\\kanikisenci\\Desktop\\miage\\Projet_Integrateur\\Spring\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Lignes.csv"))) {
            HeaderColumnNameMappingStrategy<LigneStrategie> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(LigneStrategie.class);
            CsvToBean<LigneStrategie> csvToBean = new CsvToBeanBuilder<LigneStrategie>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> {
                        CommandeEntity commande = commandeRepository.findByReference(csvLine.getReferenceCommande()).orElse(null);
                        ProduitEntity produit = produitRepository.findByReference(csvLine.getReferenceProduit()).orElse(null);

                        return LigneEntity.builder()
                                .id(csvLine.getId())
                                .commande(commande)
                                .produit(produit)
                                .quantite(csvLine.getQuantite())
                                .optionMontage(csvLine.getOptionMontage())
                                .build();
                    })
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new CsvImportRestException("Error during the importation of Lignes", e);
        }
    }
}