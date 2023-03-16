import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { map, switchMap } from "rxjs/operators";
import { of } from "rxjs";
import { Employee } from "../employee.model";
import { EmployeeService } from "../employee.service";

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {
  id!: string;
  employee!: Employee;
  feedback: any = {};
  isNew = false;
  serverError = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this
      .route
      .params
      .pipe(
        map(p => p.id),
        switchMap(id => {
          if (id === 'new') {
            this.isNew = true;
            return of(new Employee());
          }
          this.isNew = false;
          return this.employeeService.findById(id);
        })
      )
      .subscribe(employee => {
          this.employee = employee;
          this.feedback = {};
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      );
  }

  save() {
    this.employeeService.save(this.employee).subscribe(
      employee => {
        this.employee = employee;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(() => {
          this.router.navigate(['/employees']);
        }, 1000);
        this.serverError = false;
      },
      error => {
        this.feedback = {type: 'warning', message: 'Error saving'};
        if (error.status === 500) {
          this.serverError = true;
        }
      }
    );
  }

  cancel() {
    this.router.navigate(['/employees']);
  }

}
