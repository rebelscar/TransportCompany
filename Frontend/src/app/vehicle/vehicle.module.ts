import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { VEHICLE_ROUTES } from "./vehicle.routes";
import { RouterModule } from "@angular/router";
import { VehicleEditComponent } from "./vehicle-edit/vehicle-edit.component";
import { VehicleListComponent } from "./vehicle-list/vehicle-list.component";
import { VehicleService } from './vehicle.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forRoot(VEHICLE_ROUTES)
  ],
  declarations: [
    VehicleEditComponent,
    VehicleListComponent
  ],
  providers: [VehicleService],
  exports: []
})
export class VehicleModule { }
