package com.example.venteeservice.entites;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.stereotype.Service;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder

@Service
public class Client {

    private String name;
    private String adresse;

}
