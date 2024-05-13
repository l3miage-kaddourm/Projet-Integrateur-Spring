package fr.uga.l3miage.integrator.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fr.uga.l3miage.integrator.CsvStrategies.EmployeStrategie;
import fr.uga.l3miage.integrator.components.EmployeComponent;
import fr.uga.l3miage.integrator.exceptions.rest.CsvImportRestException;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundLivreursRestException;
import fr.uga.l3miage.integrator.exceptions.technical.NotFoundLivreursException;
import fr.uga.l3miage.integrator.mappers.EmployeMapper;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.repositories.EmployeRepository;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EmployeService {

    @Autowired
    private final EmployeComponent employeComponent;
    private final EmployeRepository employeRepository;
    private final EntrepotRepository entrepotRepository;
    private final EmployeMapper employeMapper;


    public Set<EmployeResponseDTO> getLivreurs() {
        try {
            return employeComponent.findAllLivreurs().stream()
                    .map(employeMapper::convertToDTO)
                    .collect(Collectors.toSet());
        } catch (NotFoundLivreursException e) {
            throw new NotFoundLivreursRestException(e.getMessage(), NotFoundLivreursRestException.Type.NOTFOUND);
        }
    }

    @DependsOn("entrepotService.importCsv")
    public void importCsv() throws IOException{
            Set<EmployeEntity> employes = parseCsv();
            employeRepository.saveAll(employes);

    }
    public Set<EmployeEntity> parseCsv() throws IOException{
        try(Reader reader = new BufferedReader(new FileReader("C:\\Users\\Mohamed AEK\\Desktop\\perso\\Etudes\\L3 UGA\\S6\\Projet\\Projet-Integrateur-Spring\\server\\src\\main\\java\\fr\\uga\\l3miage\\integrator\\data\\Employes.csv"))){
            HeaderColumnNameMappingStrategy<EmployeStrategie> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(EmployeStrategie.class);
            CsvToBean<EmployeStrategie> csvToBean =
                    new CsvToBeanBuilder<EmployeStrategie>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
           return csvToBean.parse()
                    .stream()
                   .map(csvLine  -> {
                       EntrepotEntity entrepot = entrepotRepository.findByNom(csvLine.getEntrepot());
                       return EmployeEntity.builder()
                               .trigramme(csvLine.getTrigramme())
                               .email(csvLine.getEmail())
                               .prenom(csvLine.getPrenom())
                               .nom(csvLine.getNom())
                               .photo(csvLine.getPhoto())
                               .telephone(csvLine.getTelephone())
                               .emploi(csvLine.getEmploi())
                               .entrepot(entrepot)
                               .build();
                   })
                    .collect(Collectors.toSet());
        }
        catch (IOException e){
            throw new CsvImportRestException("Error during the importation of Employs", e);
        }

    }
}

