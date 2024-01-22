package com.example.produitservice.dto;

import com.example.produitservice.entites.TypeProduit;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class ProduitRespenses {
    private Integer idP;
    private String marque;
    private TypeProduit type;
    private Integer qte;
    private Double prix;
}
