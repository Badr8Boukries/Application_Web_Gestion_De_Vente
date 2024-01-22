import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Produite} from "../Modele/Produit";

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrl: './produit.component.css'
})
export class ProduitComponent {

  produit: any;
  pr!: Produite;

  test = {
    idP: "",
    marque: "",
    type: "",
    qte: "",
    prix :""
  }

  constructor(private http: HttpClient, public router: Router) {
  }

  ngOnInit(): void {
    this.fetchProduits();
  }

  fetchProduits(): void {
    this.http.get("http://localhost:8888/PRODUIT-SERVICE/produit")
      .subscribe({
        next: data => {

          this.produit = data
        },
        error: err => {
          console.log(err);
        }
      })


  }

}
