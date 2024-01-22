import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProduitComponent} from "./produit/produit.component";
import {AuthGuard} from "./guards/auth.guard";

const routes: Routes = [
  { path : "produits", component : ProduitComponent,  canActivate:[AuthGuard], data : {roles:['ADMIN']}}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
