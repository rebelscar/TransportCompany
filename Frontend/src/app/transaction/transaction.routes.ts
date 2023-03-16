import { Routes } from "@angular/router";
import { TransactionListComponent } from "./transaction-list/transaction-list.component";
import { TransactionEditComponent } from "./transaction-edit/transaction-edit.component";


export const TRANSACTION_ROUTES: Routes = [
  {
    path: 'transactions',
    component: TransactionListComponent
  },
  {
    path: 'transactions/:id',
    component: TransactionEditComponent
  }
];
