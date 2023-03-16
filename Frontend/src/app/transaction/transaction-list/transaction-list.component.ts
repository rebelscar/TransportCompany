import { Component, OnInit } from '@angular/core';
import { ClientIdFilter } from "../client-id-filter";
import { Transaction } from '../transaction.model';
import { TransactionService } from "../transaction.service";
import { sortList } from "../../shared/util/sort.util";

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css', '../../shared/sort.css']
})
export class TransactionListComponent implements OnInit {

  filter = new ClientIdFilter();
  selectedTransaction!: Transaction;
  feedback: any = {};
  sortColumn = 'id';
  sortDirection = 'asc';
  constructor(private transactionService: TransactionService) {
  }

  get transactionList(): Transaction[] {
    return this.transactionService.transactionList;
  }

  ngOnInit() {
    this.search();
  }

  search(): void {
    this.transactionService.load(this.filter);
  }

  select(selected: Transaction): void {
    this.selectedTransaction = selected;
  }

  delete(transaction: Transaction): void {
    if (confirm('Are you sure?')) {
      this.transactionService.delete(transaction).subscribe(() => {
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
    sortList(this.transactionList, this.sortColumn, this.sortDirection);
  }
}
