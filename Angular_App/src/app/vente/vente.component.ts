import { Component } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Router} from "@angular/router";
import {Ventee} from "../Modele/Vente";
import {FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-vente',
  templateUrl: './vente.component.html',
  styleUrl: './vente.component.css'
})
export class VenteComponent {
  ventes: any;
  vn!: Ventee
  clients: any
  produits: any


  test = {
    idV: "",
    idClient: "",
    idProduit: "",

  }

  constructor(private http: HttpClient, public router: Router) {
  }

  ngOnInit(): void {
    this.fetchVentes();
    this.fetchProduits()
    this.fetchClients()
  }

  fetchVentes(): void {
    this.http.get("http://localhost:8888/VENTE-SERVICE/vente")
      .subscribe({
        next: data => {

          this.ventes = data
        },
        error: err => {
          console.log(err);
        }
      })


  }

  supprimerVente(vente: Ventee) {
    this.http.delete(`http://localhost:8888/VENTE-SERVICE/vente/${vente.idV}`)
      .subscribe({
        next: value => {
          this.fetchVentes();
        },
        error: (err: any) => {
          console.log(err);
        }
      });
  }

  confirmerSupp(vente: Ventee) {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette vente ?')) {
      this.supprimerVente(vente);
    } else {
      console.log('Suppression annulée.');
    }
  }

  upgrade(){

  }

  postVente(add: NgForm) {
    let valeur = add.value;
    let data = { idClient: valeur.idClient, idProduit: valeur.idProduit };

    this.http.post("http://localhost:8888/VENTE-SERVICE/vente", data)
      .subscribe({
        next: (value: any) => {
          this.fetchVentes()
          this.updateProductQuantity(valeur.idProduit); // Appel pour mettre à jour la quantité
        },
        error: (err: any) => {
          console.log(err);
        }
      });
  }

  private updateProductQuantity(productId: number) {
    this.http.put(`http://localhost:8888/PRODUIT-SERVICE/${productId}/decrement-quantity`, {})
      .subscribe({
        next: (value: any) => {
          console.log("Quantité mise à jour pour le produit", productId);
        },
        error: (err: any) => {
          console.log("Erreur lors de la mise à jour de la quantité du produit", err);
        }
      });
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
  fetchProduits(): void {
    this.http.get("http://localhost:8888/PRODUIT-SERVICE/produit")
      .subscribe({
        next: data => {

          this.produits = data
        },
        error: err => {
          console.log(err);
        }
      })


  }
  UpdateVente(add: NgForm) {
    alert('Vente met à jour avec succes');
    let valeur = add.value;
    if(valeur.idClient==""){
      valeur.idClient = this.vn.idClient;
    }
    if(valeur.idProduit==""){
      valeur.idProduit = this.vn.idProduit;
    }

    let vnUp = {
      idClient : valeur.idClient,
      idProduit : valeur.idProduit,

    }
    this.http.put(`http://localhost:8888/VENTE-SERVICE/vente/${this.vn.idV}`, vnUp)
      .subscribe({
        next:value => {
          this.fetchVentes();
          this.updateProductQuantity(valeur.idProduit);

        },
        error:err => {
          console.log(err)
        }
      })
  }

  prendre(cli:Ventee){
    this.vn = cli;
  }

}
