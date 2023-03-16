import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { map, switchMap } from "rxjs/operators";
import { of } from "rxjs";
import { Client } from "../client.model";
import { ClientService } from "../client.service";

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.css']
})
export class ClientEditComponent implements OnInit {

  id!: string;
  client!: Client;
  feedback: any = {};
  isNew = false;
  serverError = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService) {
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
            return of(new Client());
          }
          this.isNew = false;
          return this.clientService.findById(id);
        })
      )
      .subscribe(client => {
          this.client = client;
          this.feedback = {};
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      );
  }

  save() {
    this.clientService.save(this.client).subscribe(
      client => {
        this.client = client;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(() => {
          this.router.navigate(['/clients']);
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
    this.router.navigate(['/clients']);
  }

}
