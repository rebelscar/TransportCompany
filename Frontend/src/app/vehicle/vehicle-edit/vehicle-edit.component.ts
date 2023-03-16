import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { map, switchMap } from "rxjs/operators";
import { of } from "rxjs";
import { Vehicle } from "../vehicle.model";
import { VehicleService } from "../vehicle.service";

@Component({
  selector: 'app-vehicle-edit',
  templateUrl: './vehicle-edit.component.html',
  styleUrls: ['./vehicle-edit.component.css']
})
export class VehicleEditComponent implements OnInit {

  id!: string;
  vehicle!: Vehicle;
  feedback: any = {};
  isNew = false;
  serverError = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private vehicleService: VehicleService) {
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
            return of(new Vehicle());
          }
          this.isNew = false;
          return this.vehicleService.findById(id);
        })
      )
      .subscribe(vehicle => {
          this.vehicle = vehicle;
          this.feedback = {};
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      );
  }

  save() {
    this.vehicleService.save(this.vehicle).subscribe(
      vehicle => {
        this.vehicle = vehicle;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(() => {
          this.router.navigate(['/vehicles']);
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
    this.router.navigate(['/vehicles']);
  }

}
