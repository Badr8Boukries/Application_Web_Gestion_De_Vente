package com.example.produitservice.web;

import com.example.produitservice.config.GlobalConfig;
import com.example.produitservice.config.ProduitConfig;
import com.example.produitservice.dto.ProduitRequest;
import com.example.produitservice.dto.ProduitRespenses;
import com.example.produitservice.entites.Produit;
import com.example.produitservice.publisher.RabbitProducer;
import com.example.produitservice.repository.ProduitRepository;
import com.example.produitservice.service.ProduitInterfaceService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component


public class ProduitConroller {

    @Autowired
    ProduitInterfaceService produitInterfaceService;
    @Autowired
    RabbitProducer rabbitProducer;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    GlobalConfig globalConfig;
    @Autowired
    ProduitConfig produitConfig;

    @GetMapping("globalConfig")
    public GlobalConfig getGlobalConfig(){
        return globalConfig;
    }

    @GetMapping("/produitConfig")
    public ProduitConfig produitConfig(){
        return produitConfig;
    }

    @GetMapping("/produit/{id}")
    public ProduitRespenses getId(@PathVariable("id") Integer id) {

        return produitInterfaceService.listerById(id);
    }

    @GetMapping("/produit")
    public List<ProduitRespenses> getAll() {

        return produitInterfaceService.listerTous();
    }

    @PostMapping("/produit")
    public void add(@RequestBody ProduitRequest request) {
        rabbitProducer.envoyerMessage("Produit "+request.getMarque()+" ajouter avec succée");
        produitInterfaceService.add(request);


    }
    @PutMapping("/{productId}/decrement-quantity")
    public void decrementQuantity(@PathVariable Long productId) {
        Produit produit = produitRepository.findById(Math.toIntExact(productId))
                .orElseThrow(() -> new NotFoundException("Produit non trouvé"));

        int qte = produit.getQte();
        if (qte > 0) {
            produit.setQte(qte - 1);
            produitRepository.save(produit);
        }
        rabbitProducer.envoyerMessage("Produit  "+produit.getMarque()+" a éte consomé ");
    }

    @PutMapping("{id}/increment")
    public void increment(@PathVariable Long id){
        Produit produit=produitRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NotFoundException("Produit non trouvé"));
        int qte=produit.getQte();
        produit.setQte(qte+1);
        produitRepository.save(produit);
    }

    @PutMapping("/produit/{id}")
    public void modifier(@PathVariable ("id")Integer id,@RequestBody ProduitRequest requestq){
        produitInterfaceService.modifier(id,requestq);
        rabbitProducer.envoyerMessage("Produit de Id "+id+" modifier avec succée");

    }


    @DeleteMapping("/produit/{id}")
    public void delete(@PathVariable ("id")Integer id){
        produitInterfaceService.supprimer(id);

    }


}
