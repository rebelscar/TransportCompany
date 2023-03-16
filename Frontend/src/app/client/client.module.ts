import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { CLIENT_ROUTES } from './client.routes';
import { RouterModule } from "@angular/router";
import { ClientListComponent } from "./client-list/client-list.component";
import { ClientEditComponent } from "./client-edit/client-edit.component";
import { ClientService } from "./client.service";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forRoot(CLIENT_ROUTES)
  ],
  declarations: [
    ClientListComponent,
    ClientEditComponent
  ],
  providers: [ClientService],
  exports: []
})
export class ClientModule { }
