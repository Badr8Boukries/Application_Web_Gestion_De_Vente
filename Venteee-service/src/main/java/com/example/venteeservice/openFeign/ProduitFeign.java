package com.example.venteeservice.openFeign;

import com.example.venteeservice.entites.Produit;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUIT-SERVICE")
public interface ProduitFeign {

    @GetMapping("/produit")
   public List<Produit> getAllProduits();

    @GetMapping("/produit/{id}")
   public Produit getProduitById(@PathVariable("id") Integer id);
}