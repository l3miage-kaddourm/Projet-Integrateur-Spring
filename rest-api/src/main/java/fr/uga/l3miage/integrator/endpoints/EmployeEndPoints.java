package fr.uga.l3miage.integrator.endpoints;


import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/employes")
public interface EmployeEndPoints {


    @RequestMapping("/livreurs")
    Set<EmployeResponseDTO> getAllLivreurs();
}
