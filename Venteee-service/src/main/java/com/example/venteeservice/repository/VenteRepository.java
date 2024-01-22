package com.example.venteeservice.repository;

import com.example.venteeservice.entites.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente,Integer> {
}
