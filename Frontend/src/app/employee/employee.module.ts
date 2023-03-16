import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { EMPLOYEE_ROUTES } from "./employee.routes";
import { EmployeeListComponent } from "./employee-list/employee-list.component";
import { EmployeeEditComponent } from "./employee-edit/employee-edit.component";
import { EmployeeService } from "./employee.service";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forRoot(EMPLOYEE_ROUTES)
  ],
  declarations: [
    EmployeeListComponent,
    EmployeeEditComponent
  ],
  providers: [EmployeeService],
  exports: []
})
export class EmployeeModule { }
