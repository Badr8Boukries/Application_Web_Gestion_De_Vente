package com.example.venteeservice.web;

import com.example.venteeservice.entites.Client;
import com.example.venteeservice.entites.Produit;
import com.example.venteeservice.entites.Vente;
import com.example.venteeservice.event.VenteEvent;
import com.example.venteeservice.openFeign.ClientFeign;
import com.example.venteeservice.openFeign.ProduitFeign;
import com.example.venteeservice.publisher.RabbitProducer;
import com.example.venteeservice.repository.VenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class Vente_Controller {

    @Autowired
    RabbitProducer rabbitProducer;
    private final VenteRepository repository;
    private final ClientFeign achteurFeignController;
    private final ProduitFeign produitFeignController;



    @Autowired
    public Vente_Controller(VenteRepository venteRepository, ClientFeign clientFeign, ProduitFeign produitFeign, KafkaTemplate kafkaTemplate) {
        this.repository = venteRepository;
        this.achteurFeignController = clientFeign;
        this.produitFeignController = produitFeign;
    }

    @GetMapping("/vente")
    public List<Vente> getAllVente() {
        List<Vente> lv = repository.findAll();

        for (Vente v : lv) {
            Produit p = produitFeignController.getProduitById(v.getIdProduit());
            Client a = achteurFeignController.getClientById(v.getIdClient());

            v.setProduit(p);
            v.setClient(a);
        }

        return lv;
    }

    @GetMapping("/vente/{id}")
    public Vente getVenteById(@PathVariable("id") Integer id) {
        Vente v = repository.findById(id).orElse(null);

        if (v != null) {
            Produit p = produitFeignController.getProduitById(v.getIdProduit());
            Client a = achteurFeignController.getClientById(v.getIdClient());

            v.setProduit(p);
            v.setClient(a);
        }

        return v;
    }


    @PutMapping("/vente/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody Vente vente){
        Vente v = repository.findById(id).get();

        if(vente.getIdClient() != null)
            v.setIdClient(vente.getIdClient());
        if(vente.getIdProduit() != null)
            v.setIdProduit(vente.getIdProduit());


        rabbitProducer.envoyerMessage("Vente modifier avec succée");

        repository.save(v);
}
    @PostMapping("/vente")
    public void save(@RequestBody Vente vente){
        repository.save(vente);
        rabbitProducer.envoyerMessage("Vente ajouter avec succée");

    }




    @DeleteMapping("/vente/{id}")
    public void delete(@PathVariable("id")Integer id){
        rabbitProducer.envoyerMessage("Vente supprimer avec succée");
        repository.deleteById(id);

    }


}
