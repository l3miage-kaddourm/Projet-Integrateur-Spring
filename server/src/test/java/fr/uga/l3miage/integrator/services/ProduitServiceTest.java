package fr.uga.l3miage.integrator.services;


import fr.uga.l3miage.integrator.models.ProduitEntity;
import fr.uga.l3miage.integrator.repositories.ProduitRepository;
import fr.uga.l3miage.integrator.responses.ProduitResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureTestDatabase
public class ProduitServiceTest {

    @MockBean
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitService produitService;

    @Test
    public void getAllProducts_ShouldReturnProducts_WhenTheyExist() {
        // Given
        Set<ProduitEntity> mockProduits = new HashSet<>();
        ProduitEntity produit1 = ProduitEntity.builder()
                .reference("REF001")
                .photo("photo1.jpg")
                .titre("Produit 1")
                .description("Description 1")
                .prix(100.0)
                .build();

        ProduitEntity produit2 = ProduitEntity.builder()
                .reference("REF002")
                .photo("photo2.jpg")
                .titre("Produit 2")
                .description("Description 2")
                .prix(200.0)
                .build();

        mockProduits.add(produit1);
        mockProduits.add(produit2);

        when(produitRepository.findAllBy()).thenReturn(mockProduits);

        // When
        Set<ProduitResponseDTO> result = produitService.getAllProducts();

        // Then
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void getAllProducts_ShouldReturnEmptySet_WhenNoProductsExist() {
        // Given
        when(produitRepository.findAllBy()).thenReturn(new HashSet<>());

        // When
        Set<ProduitResponseDTO> result = produitService.getAllProducts();

        // Then
        assertEquals(0, result.size());
    }

}
