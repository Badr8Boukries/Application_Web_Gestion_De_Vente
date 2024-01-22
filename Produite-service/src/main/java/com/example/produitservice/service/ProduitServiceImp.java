package com.example.produitservice.service;

import com.example.produitservice.dto.ProduitRequest;
import com.example.produitservice.dto.ProduitRespenses;
import com.example.produitservice.entites.Produit;
import com.example.produitservice.mapper.ProduitInterfaceMapper;
import com.example.produitservice.repository.ProduitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Transactional
public class ProduitServiceImp implements ProduitInterfaceService{
    @Autowired
    ProduitInterfaceMapper produitInterfaceMapper;
    @Autowired
    ProduitRepository produitRepository;
    @Override
    public void add(ProduitRequest req) {
        Produit p=new Produit();
        p=produitInterfaceMapper.REQUEST_TO_PRODUIT(req);
        produitRepository.save(p);

    }

    @Override
    public ProduitRespenses listerById(Integer id) {
        Produit p=produitRepository.findById(id).get();
        ProduitRespenses res= new ProduitRespenses();
        res=produitInterfaceMapper.PRODUIT_TO_RESPENSES(p);

        return res;
    }

    @Override
    public List<ProduitRespenses> listerTous() {
        List<Produit> lp=new ArrayList<Produit>();

        lp=produitRepository.findAll();

        List<ProduitRespenses>lr=new ArrayList<ProduitRespenses>();

        for(Produit p :lp){

            ProduitRespenses res=new ProduitRespenses();
            res=produitInterfaceMapper.PRODUIT_TO_RESPENSES(p);
            lr.add(res);
        }

        return lr;
    }

    @Override
    public void modifier(Integer id,ProduitRequest req) {
        Produit p=produitRepository.findById(id).get();
        if(p.getMarque()!=null)
            p.setMarque(req.getMarque());
        if(p.getType()!=null)
            p.setType(req.getType());
        if(p.getQte()!=null)
            p.setQte(req.getQte());
        if(p.getPrix()!=null)
            p.setPrix(req.getPrix());

        produitRepository.save(p);


    }

    @Override
    public void supprimer(Integer id) {
        produitRepository.deleteById(id);

    }
}
