package com.example.produitservice.mapper;

import com.example.produitservice.dto.ProduitRequest;
import com.example.produitservice.dto.ProduitRespenses;
import com.example.produitservice.entites.Produit;

public interface ProduitInterfaceMapper {

    public Produit REQUEST_TO_PRODUIT(ProduitRequest req);

    public ProduitRespenses PRODUIT_TO_RESPENSES(Produit p);

}
