package com.example.produitservice.mapper;

import com.example.produitservice.dto.ProduitRequest;
import com.example.produitservice.dto.ProduitRespenses;
import com.example.produitservice.entites.Produit;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapperimp implements ProduitInterfaceMapper{

    @Override
    public Produit REQUEST_TO_PRODUIT(ProduitRequest req) {
        Produit p=new Produit();
        p.setType(req.getType());
        p.setMarque(req.getMarque());
        p.setQte(req.getQte());
        p.setPrix(req.getPrix());

        return p;
    }

    @Override
    public ProduitRespenses PRODUIT_TO_RESPENSES(Produit p) {
        ProduitRespenses res=new ProduitRespenses();
        res.setType(p.getType());
        res.setMarque(p.getMarque());
        res.setIdP(p.getIdP());
        res.setQte(p.getQte());
        res.setPrix(p.getPrix());
        return res;
    }
}
