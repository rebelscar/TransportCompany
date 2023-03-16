import { Component, OnInit } from '@angular/core';
import { BrandFilter } from "../brand-filter";
import { Vehicle } from "../vehicle.model";
import { VehicleService } from "../vehicle.service";
import { sortList } from "../../shared/util/sort.util";

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css', '../../shared/sort.css']
})
export class VehicleListComponent implements OnInit {

  filter = new BrandFilter();
  selectedVehicle!: Vehicle;
  feedback: any = {};
  sortColumn = 'id';
  sortDirection = 'asc';
  constructor(private vehicleService: VehicleService) {
  }

  get vehicleList(): Vehicle[] {
    return this.vehicleService.vehicleList;
  }

  ngOnInit() {
    this.search();
  }

  search(): void {
    this.vehicleService.load(this.filter);
  }

  select(selected: Vehicle): void {
    this.selectedVehicle = selected;
  }

  delete(vehicle: Vehicle): void {
    if (confirm('Are you sure?')) {
      this.vehicleService.delete(vehicle).subscribe(() => {
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
    if (this.sortColumn === 'id') {
      sortList(this.vehicleList, this.sortColumn, this.sortDirection);
    } else {
      this.vehicleList.sort((a: Vehicle, b: Vehicle) => {
        let [detail, field] = column.split(".");
        // @ts-ignore
        let value1 = a[detail][field];
        if(isNaN(Number(value1))) value1 = value1.toLowerCase();

        // @ts-ignore
        let value2 = b[detail][field];
        if(isNaN(Number(value2))) value2 = value2.toLowerCase();

        let comparison = 0;
        if (value1 > value2) {
          comparison = 1;
        } else if (value1 < value2) {
          comparison = -1;
        }
        return this.sortDirection === 'asc' ? comparison : comparison * -1;
      });
    }
  }
}
