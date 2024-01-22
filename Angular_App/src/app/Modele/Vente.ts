import {Cliente} from "./Client";
import {Produite} from "./Produit";


export interface Ventee {
  idV: number
  idClient: number
  idProduit: number
  client: Cliente
  produit: Produite
}
