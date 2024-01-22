package com.example.venteeservice.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity

public class Vente {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idV;
    private  Double benfice;
    private Integer idClient;
    private Integer idProduit;

    @Transient
    public Client client;
    @Transient
    public Produit  produit;


}
