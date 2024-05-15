package fr.uga.l3miage.integrator.services;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fr.uga.l3miage.integrator.CsvStrategies.CamionStrategie;
import fr.uga.l3miage.integrator.CsvStrategies.ProduitStrategie;
import fr.uga.l3miage.integrator.DataType.GeoPosition;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.models.CamionEntity;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.ProduitEntity;
import fr.uga.l3miage.integrator.repositories.ProduitRepository;
import fr.uga.l3miage.integrator.responses.ProduitResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    ProduitRepository produitRepository;;


    public Set<ProduitResponseDTO> getAllProducts() {
        return produitRepository.findAllBy()
                .stream()
                .map(produitEntity -> ProduitResponseDTO.builder()
                        .reference(produitEntity.getReference())
                        .photo(produitEntity.getPhoto())
                        .titre(produitEntity.getTitre())
                        .description(produitEntity.getDescription())
                        .prix(produitEntity.getPrix())
                        .optionMontage(produitEntity.isOptionMontage())
                        .build())
                .collect(Collectors.toSet());
    }


    public void importCsv() throws IOException {
        Set<ProduitEntity> produits = parseCsv();
        produitRepository.saveAll(produits);

    }


    public Set<ProduitEntity> parseCsv() throws IOException {
        try (Reader reader = new BufferedReader(new FileReader("C:\\Users\\Mohamed AEK\\Desktop\\Projet-Integrateur-Springc copy\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Produits.csv"))) {
            HeaderColumnNameMappingStrategy<ProduitStrategie> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(ProduitStrategie.class);
            CsvToBean<ProduitStrategie> csvToBean =
                    new CsvToBeanBuilder<ProduitStrategie>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();
            return csvToBean.parse()
                    .stream()
                    .map(csvLine -> ProduitEntity.builder()
                            .reference(csvLine.getReference())
                            .photo(csvLine.getPhoto())
                            .titre(csvLine.getTitre())
                            .description(csvLine.getDescription())
                            .prix(csvLine.getPrix())
                            .optionMontage(csvLine.isOptionMontage())
                            .build())
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new CsvImportRestException("Error during the importation of Camions", e);
        }
    }

}
