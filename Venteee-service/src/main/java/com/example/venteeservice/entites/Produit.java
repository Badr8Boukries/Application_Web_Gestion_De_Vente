package com.example.venteeservice.entites;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder

@Service
public class Produit {

    private String marque;
    private TypeProduit type;
    private Integer qte;

    private LocalDate dateAjout;
}
