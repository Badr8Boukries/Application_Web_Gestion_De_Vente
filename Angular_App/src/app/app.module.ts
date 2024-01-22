import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {CommonModule, NgForOf} from "@angular/common";
import {RouterLink, RouterOutlet} from "@angular/router";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ProduitComponent } from './produit/produit.component';
import { ClientComponent } from './client/client.component';
import { VenteComponent } from './vente/vente.component';
import {HomeComponent} from "./home/home.component";

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'angu-app',
        clientId: 'angular-badr'
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,

    ProduitComponent,
     ClientComponent,
     VenteComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgForOf,
    RouterLink,
    RouterOutlet,
    KeycloakAngularModule,
    FormsModule,
    NgForOf,
    ReactiveFormsModule,
    RouterOutlet,
    FormsModule,
    NgForOf,
    FormsModule,
    NgForOf,
    HomeComponent,
    FormsModule,
    NgForOf,
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: APP_INITIALIZER, deps : [KeycloakService],useFactory : initializeKeycloak, multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
