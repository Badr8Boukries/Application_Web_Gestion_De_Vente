package com.example.produitservice;

import com.example.produitservice.config.GlobalConfig;
import com.example.produitservice.config.ProduitConfig;
import com.example.produitservice.entites.Produit;
import com.example.produitservice.entites.TypeProduit;
import com.example.produitservice.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import lombok.*;

import java.time.LocalDate;

import static com.example.produitservice.entites.TypeProduit.PC;
import static com.example.produitservice.entites.TypeProduit.PHONE;

@EnableConfigurationProperties({GlobalConfig.class, ProduitConfig.class})

@SpringBootApplication
public class ProduitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProduitRepository produitRepository) {
        return args -> {
            Produit p= Produit.builder()
                    .type(PHONE)
                    .marque("samsung")
                    .qte(100)
                    .prix(4000.0)
                    .dateAjout(LocalDate.ofYearDay(2023,23))
                    .build();
            produitRepository.save(p);

            System.out.println("produit : "+p.getMarque() +" et de type "+p.getType()+" cree le : "+p.getDateAjout());
            Produit p1= Produit.builder()
                    .type(PHONE)
                    .marque("Xiaomi")
                    .qte(150)
                    .prix(3000.0)
                    .dateAjout(LocalDate.ofYearDay(2023,11))
                    .build();
            produitRepository.save(p1);

            System.out.println("produit : "+p1.getMarque() +" et de type "+p1.getType()+" cree le : "+p1.getDateAjout());

            Produit p2= Produit.builder()
                    .type(PC)
                    .marque("Hp")
                    .qte(114)
                    .prix(12000.0)
                    .dateAjout(LocalDate.ofYearDay(2025,23))
                    .build();
            produitRepository.save(p2);
            Produit p3= Produit.builder()
                    .type(PC)
                    .marque("DELL")
                    .qte(122)
                    .prix(8500.0)
                    .dateAjout(LocalDate.ofYearDay(2023,23))
                    .build();
            produitRepository.save(p3);
            Produit p4= Produit.builder()
                    .type(PHONE)
                    .marque("HUWAWEI")
                    .qte(100)
                    .prix(3200.0)
                    .dateAjout(LocalDate.ofYearDay(2022,13))
                    .build();
            produitRepository.save(p4);

            System.out.println("produit : "+p2.getMarque() +" et de type "+p2.getType()+" cree le : "+p2.getDateAjout());

        };


    }
}
