package com.example.produitservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "produit.params")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitConfig {
    int x;

    int y;
}
