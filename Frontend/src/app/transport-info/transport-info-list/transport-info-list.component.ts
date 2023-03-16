import { Component, OnInit } from '@angular/core';
import { TransportInfoService } from "../transport-info.service";
import { TransportInfo } from "../transport-info.model";
import { DestinationFilter } from "../destination-filter";
import { sortList } from "../../shared/util/sort.util";

@Component({
  selector: 'app-transport-info-list',
  templateUrl: './transport-info-list.component.html',
  styleUrls: ['./transport-info-list.component.css', '../../shared/sort.css']
})
export class TransportInfoListComponent implements OnInit {

  filter = new DestinationFilter();
  selectedInfo!: TransportInfo;
  feedback: any = {};
  sortColumn = 'id';
  sortDirection = 'asc';
  constructor(private transportInfoService: TransportInfoService) {
  }

  get transportInfoList(): TransportInfo[] {
    return this.transportInfoService.transportInfoList;
  }

  ngOnInit() {
    this.search();
  }

  search(): void {
    this.transportInfoService.load(this.filter);
  }

  select(selected: TransportInfo): void {
    this.selectedInfo = selected;
  }

  delete(transportInfo: TransportInfo): void {
    if (confirm('Are you sure?')) {
      this.transportInfoService.delete(transportInfo).subscribe(() => {
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
    sortList(this.transportInfoList, this.sortColumn, this.sortDirection);
  }
}
