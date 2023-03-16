import { Routes } from "@angular/router";
import { EmployeeListComponent } from "./employee-list/employee-list.component";
import { EmployeeEditComponent } from "./employee-edit/employee-edit.component";

export const EMPLOYEE_ROUTES: Routes = [
  {
    path: 'employees',
    component: EmployeeListComponent
  },
  {
    path: 'employees/:id',
    component: EmployeeEditComponent
  }
];
