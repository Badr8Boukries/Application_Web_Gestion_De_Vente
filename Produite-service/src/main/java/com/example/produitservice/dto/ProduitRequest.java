package com.example.produitservice.dto;

import com.example.produitservice.entites.TypeProduit;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class ProduitRequest {

    private String marque;
    private TypeProduit type;
    private Integer qte;
    private Double prix;
}
