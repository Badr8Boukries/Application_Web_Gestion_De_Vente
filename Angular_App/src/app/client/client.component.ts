import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {AuthGuard} from "../guards/auth.guard";
import {NgForm} from "@angular/forms";
import {Cliente} from "../Modele/Client";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrl: './client.component.css'
})
export class ClientComponent {

  clients: any;
  cl!:Cliente;


  test ={
    idC :"",
    name :"",
    adresse:"",
    email :""
  }

  constructor(private http :HttpClient,public router:Router,private K:KeycloakService,private km:KeycloakAngularModule,private n:AuthGuard) {}

  ngOnInit(): void {
    this.fetchClients();
  }

  fetchClients(): void {
    this.http.get("http://localhost:8888/CLIENT-SERVICE/client")
      .subscribe({
        next: data => {

          this.clients = data
        },
        error: err => {
          console.log(err);
        }
      })


  }

  supprimerClient(idC: number): void {
    console.log(idC);
    this.http.delete(`http://localhost:8888/CLIENT-SERVICE/client/${idC}`)
      .subscribe({
        next: () => {
          this.fetchClients();
        },
        error: (err: any) => {
          console.log(err);
        }
      });
  }
  confirmerSuppression(clientId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce client ?')) {
      this.supprimerClient(clientId);
    } else {
      console.log('Suppression annulée.');
    }

  }
  postclient(add:NgForm){
    let valeur= add.value;
    let data = {name :valeur.name, adresse:valeur.adresse,email: valeur.email}
    this.http.post("http://localhost:8888/CLIENT-SERVICE/client", data)
      .subscribe({
        next:value => {
          this.fetchClients();

        },
        error:err => {
          console.log(err)
        }
      })
  }

  updateClient(upd: NgForm): void {

    alert("update successful");
    let valeur = upd.value;
    if(valeur.name==""){
      valeur.name=this.cl.name;
    }
    if(valeur.adresse==""){
      valeur.adresse=this.cl.adresse;
    }
    if(valeur.email==""){
      valeur.email=this.cl.email;
    }
    let ce= {name:valeur.name, adresse:valeur.adresse, email:valeur.email};

    this.http.put(`http://localhost:8888/CLIENT-SERVICE/client/${this.cl.idC}`, ce)
      .subscribe({
        next: (value: any) => {
          this.fetchClients(); //
        },
        error: (err: any) => {
          console.log(err);

        }
      });
  }


  prendre(cli:Cliente){
    this.cl = cli;
  }

}
