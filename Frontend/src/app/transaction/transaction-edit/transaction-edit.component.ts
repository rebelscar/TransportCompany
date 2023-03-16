import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { map, switchMap } from "rxjs/operators";
import { of } from "rxjs";
import { Transaction } from "../transaction.model";
import { TransactionService } from "../transaction.service";

@Component({
  selector: 'app-transaction-edit',
  templateUrl: './transaction-edit.component.html',
  styleUrls: ['./transaction-edit.component.css']
})
export class TransactionEditComponent implements OnInit {

  id!: string;
  transaction!: Transaction;
  feedback: any = {};
  isNew = false;
  serverError = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private transactionService: TransactionService) {
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
            return of(new Transaction());
          }
          this.isNew = false;
          return this.transactionService.findById(id);
        })
      )
      .subscribe(transaction => {
          this.transaction = transaction;
          this.feedback = {};
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      );
  }

  save() {
    this.transactionService.save(this.transaction).subscribe(
      transaction => {
        this.transaction = transaction;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        setTimeout(() => {
          this.router.navigate(['/transactions']);
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
    this.router.navigate(['/transactions']);
  }


}
