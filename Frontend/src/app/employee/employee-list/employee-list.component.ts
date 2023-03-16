import { Component, OnInit } from '@angular/core';
import { FromToFilter } from 'src/app/shared/from-to-filter';
import { Employee } from "../employee.model";
import { EmployeeService } from "../employee.service";
import { sortList } from "../../shared/util/sort.util";

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css', '../../shared/sort.css']
})
export class EmployeeListComponent implements OnInit {
  filter = new FromToFilter();
  selectedEmployee!: Employee;
  feedback: any = {};
  sortColumn = 'id';
  sortDirection = 'asc';
  constructor(private employeeService: EmployeeService) {
  }

  get employeeList(): Employee[] {
    return this.employeeService.employeeList;
  }

  ngOnInit() {
    this.search();
  }

  search(): void {
    this.employeeService.load(this.filter);
  }

  select(selected: Employee): void {
    this.selectedEmployee = selected;
  }

  delete(employee: Employee): void {
    if (confirm('Are you sure?')) {
      this.employeeService.delete(employee).subscribe(() => {
          this.feedback = {type: 'success', message: 'Deletion was successful!'};
          setTimeout(() => {
            this.search();
          }, 1000);
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error deleting.'};
        }
      );
    }
  }

  sort(column: string) {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    sortList(this.employeeList, this.sortColumn, this.sortDirection);
  }

}
