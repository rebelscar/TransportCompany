import { HttpClientModule } from '@angular/common/http';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { APP_EXTRA_OPTIONS, APP_ROUTES } from './app.routes';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { EmployeeComponent } from './employee/employee.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { ClientComponent } from './client/client.component';
import { EmployeeModule } from "./employee/employee.module";
import { TransportInfoComponent } from './transport-info/transport-info.component';
import { TransportInfoModule } from './transport-info/transport-info.module';
import { VehicleModule } from "./vehicle/vehicle.module";
import { TransactionComponent } from './transaction/transaction.component';
import { ClientModule } from "./client/client.module";
import { TransactionModule } from "./transaction/transaction.module";

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    EmployeeModule,
    TransportInfoModule,
    VehicleModule,
    ClientModule,
    TransactionModule,
    RouterModule.forRoot([...APP_ROUTES], {...APP_EXTRA_OPTIONS}),
  ],
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    HomeComponent,
    EmployeeComponent,
    VehicleComponent,
    ClientComponent,
    TransportInfoComponent,
    TransactionComponent
  ],
  providers: [],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
