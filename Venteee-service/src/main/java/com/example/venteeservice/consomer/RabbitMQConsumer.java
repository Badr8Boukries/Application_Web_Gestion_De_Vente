package com.example.venteeservice.consomer;


import com.example.venteeservice.entites.Vente;
import com.example.venteeservice.publisher.RabbitProducer;
import com.example.venteeservice.repository.VenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQConsumer {
    @Autowired
    VenteRepository venteRepository;
    @Autowired
    RabbitProducer rabbitProducer;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @RabbitListener(queues = {"${rabbitmq.queue3.name}"})
    public void consume(Integer id){
        LOGGER.info(String.format("Message consommé et vente supprimer -> %d", id));
        List<Vente> ListeVente=venteRepository.findAll();
        for(Vente v: ListeVente){
            int ia=v.getIdClient();
            int iv=v.getIdV();
            if(ia==id){

                venteRepository.deleteById(iv);


            }
        }
    }
}
