import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthGuard} from "./guards/auth.guard";
import {ProduitComponent} from "./produit/produit.component";
import {ClientComponent} from "./client/client.component";
import {VenteComponent} from "./vente/vente.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  { path : "produits", component : ProduitComponent, canActivate:[AuthGuard], data : {roles:['ADMIN']} },
  { path : "clients", component : ClientComponent, canActivate:[AuthGuard], data : {roles:['ADMIN']} },
  { path : "ventes", component : VenteComponent },
  { path : "home", component : HomeComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
