import { Injectable } from '@angular/core';
import { CrudOperations } from "../shared/crud-operations";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { EMPTY, Observable } from "rxjs";
import { TransportInfo } from "./transport-info.model";
import { DestinationFilter } from "./destination-filter";

@Injectable({
  providedIn: 'root'
})
export class TransportInfoService implements CrudOperations {

  baseUrl = 'http://localhost:8080/transport_info';
  transportInfoList: TransportInfo[] = [];

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<TransportInfo> {
    const url = `${this.baseUrl}/${id}`;
    const params = { 'id': id };
    const headers = new HttpHeaders().set('Accept', 'application/json');
    return this.http.get<TransportInfo>(url, {params, headers});
  }

  load(filter: DestinationFilter): void {
    this.find(filter).subscribe(result => {
        this.transportInfoList = result;
      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(filter: DestinationFilter): Observable<TransportInfo[]> {
    const headers = new HttpHeaders().set('Accept', 'application/json');

    const params = {
      'destination': filter.destination,
    };

    return this.http.get<TransportInfo[]>(this.baseUrl, {params, headers});
  }

  save(entity: TransportInfo): Observable<TransportInfo> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.put<TransportInfo>(url, entity, {headers, params});
      // TODO: PUT mapping in backend
    } else {
      return this.http.post<TransportInfo>(this.baseUrl, entity, {headers, params});
    }
  }

  delete(entity: TransportInfo): Observable<TransportInfo> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');
    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.delete<TransportInfo>(url, {headers, params});
    }
    return EMPTY;
  }
}
