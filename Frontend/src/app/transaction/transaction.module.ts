import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { TRANSACTION_ROUTES } from "./transaction.routes";
import { RouterModule } from "@angular/router";
import { TransactionService } from "./transaction.service";
import { TransactionEditComponent } from "./transaction-edit/transaction-edit.component";
import { TransactionListComponent } from "./transaction-list/transaction-list.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forRoot(TRANSACTION_ROUTES)
  ],
  declarations: [
    TransactionEditComponent,
    TransactionListComponent
  ],
  providers: [TransactionService],
  exports: []
})
export class TransactionModule { }
