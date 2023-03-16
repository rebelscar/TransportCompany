import { Routes } from "@angular/router";
import { VehicleListComponent } from "./vehicle-list/vehicle-list.component";
import { VehicleEditComponent } from "./vehicle-edit/vehicle-edit.component";


export const VEHICLE_ROUTES: Routes = [
  {
    path: 'vehicles',
    component: VehicleListComponent
  },
  {
    path: 'vehicles/:id',
    component: VehicleEditComponent
  }
];
