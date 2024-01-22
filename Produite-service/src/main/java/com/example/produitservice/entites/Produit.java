package com.example.produitservice.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor  @ToString @Data @Builder
public class Produit {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idP;
    private String marque;
    private TypeProduit type;
    private Double prix;
    private Integer qte;
    private LocalDate dateAjout;

    public Produit() {
        this.dateAjout = LocalDate.now(); // Initialise la date d'ajout à la date actuelle lors de la création de l'objet
    }
}
