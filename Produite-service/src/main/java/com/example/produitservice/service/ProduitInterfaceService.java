package com.example.produitservice.service;

import com.example.produitservice.dto.ProduitRequest;
import com.example.produitservice.dto.ProduitRespenses;
import com.example.produitservice.entites.Produit;

import java.util.List;

public interface ProduitInterfaceService {

    //ADD
     public void add(ProduitRequest req);

    //GET_ID
    public ProduitRespenses listerById(Integer id);

    //GET
    public List<ProduitRespenses> listerTous();

    //PUT
    public void modifier(Integer id,ProduitRequest request);

    //DELETE

    public void supprimer(Integer id);


}
