import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransportInfoEditComponent } from './transport-info-edit/transport-info-edit.component';
import { TransportInfoListComponent } from './transport-info-list/transport-info-list.component';
import { FormsModule } from "@angular/forms";
import { TRANSPORT_INFO_ROUTES } from "./transport-info.routes";
import { RouterModule } from "@angular/router";
import { TransportInfoService } from './transport-info.service';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forRoot(TRANSPORT_INFO_ROUTES)
  ],
  declarations: [
    TransportInfoEditComponent,
    TransportInfoListComponent
  ],
  providers: [TransportInfoService],
  exports: []
})
export class TransportInfoModule { }
