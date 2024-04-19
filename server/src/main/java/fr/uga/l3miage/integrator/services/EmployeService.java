package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.EmployeComponent;
import fr.uga.l3miage.integrator.models.EmployeEntity;
import fr.uga.l3miage.integrator.responses.EmployeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeService {


    private final EmployeComponent employeComponent;

    @Autowired
    public EmployeService(EmployeComponent employeComponent) {
        this.employeComponent = employeComponent;
    }
    public EmployeResponseDTO convertToDTO(EmployeEntity employeEntity) {
        EmployeResponseDTO employeResponseDTO = new EmployeResponseDTO();
        employeResponseDTO.setTrigramme(employeEntity.getTrigramme());
        employeResponseDTO.setEmail(employeEntity.getEmail());
        employeResponseDTO.setPrenom(employeEntity.getPrenom());
        employeResponseDTO.setNom(employeEntity.getNom());
        employeResponseDTO.setPhoto(employeEntity.getPhoto());
        employeResponseDTO.setTelephone(employeEntity.getTelephone());
        employeResponseDTO.setEmploi(employeEntity.getEmploi());
        return employeResponseDTO;
    }
}
