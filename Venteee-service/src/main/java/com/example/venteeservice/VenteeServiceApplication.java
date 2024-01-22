package com.example.venteeservice;

import com.example.venteeservice.entites.Vente;
import com.example.venteeservice.repository.VenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class VenteeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VenteeServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(VenteRepository repository) {
        return args -> {

            Vente v1 = Vente.builder()

                    .idClient(1)
                    .idProduit(1)
                    .build();
            repository.save(v1);
            Vente v2 = Vente.builder()

                    .idClient(1)
                    .idProduit(2)
                    .build();
            repository.save(v2);
            Vente v3 = Vente.builder()

                    .idClient(2)
                    .idProduit(2)

                    .build();
            repository.save(v3);



            System.out.println(" "+v1.produit+" "+v1.client);
            System.out.println("marche bien");

        };

    }}
