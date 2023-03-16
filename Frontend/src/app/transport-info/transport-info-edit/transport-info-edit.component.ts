import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { map, switchMap } from "rxjs/operators";
import { of } from "rxjs";
import { TransportInfo } from "../transport-info.model";
import { TransportInfoService } from "../transport-info.service";

@Component({
  selector: 'app-transport-info-edit',
  templateUrl: './transport-info-edit.component.html',
  styleUrls: ['./transport-info-edit.component.css']
})
export class TransportInfoEditComponent implements OnInit {

  id!: string;
  transportInfo!: TransportInfo;
  feedback: any = {};
  isNew = false;
  serverError = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private transportInfoService: TransportInfoService) {
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
            return of(new TransportInfo());
          }
          this.isNew = false;
          return this.transportInfoService.findById(id);
        })
      )
      .subscribe(transportInfo => {
          this.transportInfo = transportInfo;
          this.feedback = {};
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      );
  }

  save() {
    this.transportInfoService.save(this.transportInfo).subscribe(
      transportInfo => {
        this.transportInfo = transportInfo;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(() => {
          this.router.navigate(['/transport_info']);
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
    this.router.navigate(['/transport_info']);
  }

}
