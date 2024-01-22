package com.example.venteeservice.openFeign;

import com.example.venteeservice.entites.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientFeign {


    @GetMapping("/client")
    List<Client> getAllClients();

    @GetMapping("/client/{id}")
    Client getClientById(@PathVariable("id") Integer id);
}
