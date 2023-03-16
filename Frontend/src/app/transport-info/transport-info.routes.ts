import { Routes } from "@angular/router";
import { TransportInfoListComponent } from "./transport-info-list/transport-info-list.component";
import { TransportInfoEditComponent } from "./transport-info-edit/transport-info-edit.component";


export const TRANSPORT_INFO_ROUTES: Routes = [
  {
    path: 'transport_info',
    component: TransportInfoListComponent
  },
  {
    path: 'transport_info/:id',
    component: TransportInfoEditComponent
  }
];
