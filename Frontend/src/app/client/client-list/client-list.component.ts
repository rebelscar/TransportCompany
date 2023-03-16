import { Component, OnInit } from '@angular/core';
import { Client } from '../client.model';
import { FirstNameFilter } from "../first-name-filter";
import { ClientService } from "../client.service";
import { sortList } from "../../shared/util/sort.util";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css', '../../shared/sort.css']
})
export class ClientListComponent implements OnInit {

  filter = new FirstNameFilter();
  selectedClient!: Client;
  feedback: any = {};
  sortColumn = 'id';
  sortDirection = 'asc';
  constructor(private clientService: ClientService) {
  }

  get clientList(): Client[] {
    return this.clientService.employeeList;
  }

  ngOnInit() {
    this.search();
  }

  search(): void {
    this.clientService.load(this.filter);
  }

  select(selected: Client): void {
    this.selectedClient = selected;
  }

  delete(client: Client): void {
    if (confirm('Are you sure?')) {
      this.clientService.delete(client).subscribe(() => {
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
    sortList(this.clientList, this.sortColumn, this.sortDirection);
  }
}
