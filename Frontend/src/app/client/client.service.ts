import { Injectable } from '@angular/core';
import { CrudOperations } from "../shared/crud-operations";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { EMPTY, Observable } from "rxjs";
import { Client } from "./client.model";
import { FirstNameFilter } from "./first-name-filter";

@Injectable({
  providedIn: 'root'
})
export class ClientService implements CrudOperations {

  baseUrl = 'http://localhost:8080/clients';
  employeeList: Client[] = [];

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Client> {
    const url = `${this.baseUrl}/${id}`;
    const params = { 'id': id };
    const headers = new HttpHeaders().set('Accept', 'application/json');

    return this.http.get<Client>(url, {params, headers});
  }

  load(filter: FirstNameFilter): void {
    this.find(filter).subscribe(result => {
        this.employeeList = result;
      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(filter: FirstNameFilter): Observable<Client[]> {
    const headers = new HttpHeaders().set('Accept', 'application/json');

    const params = {
      'name': filter.name
    };

    return this.http.get<Client[]>(this.baseUrl, {params, headers});
  }

  save(entity: Client): Observable<Client> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.put<Client>(url, entity, {headers, params});
      // TODO: PUT mapping in backend
    } else {
      return this.http.post<Client>(this.baseUrl, entity, {headers, params});
    }
  }

  delete(entity: Client): Observable<Client> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.delete<Client>(url, {headers, params});
    }
    return EMPTY;
  }
}
