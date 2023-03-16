import { Injectable } from '@angular/core';
import { CrudOperations } from "../shared/crud-operations";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { EMPTY, Observable } from "rxjs";
import { Transaction } from "./transaction.model";
import { ClientIdFilter } from "./client-id-filter";

@Injectable({
  providedIn: 'root'
})
export class TransactionService implements CrudOperations {

  baseUrl = 'http://localhost:8080/transactions';
  transactionList: Transaction[] = [];

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Transaction> {
    const url = `${this.baseUrl}/${id}`;
    const params = { 'id': id };
    const headers = new HttpHeaders().set('Accept', 'application/json');

    return this.http.get<Transaction>(url, {params, headers});
  }

  load(filter: ClientIdFilter): void {
    this.find(filter).subscribe(result => {
        this.transactionList = result;
      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(filter: ClientIdFilter): Observable<Transaction[]> {
    const headers = new HttpHeaders().set('Accept', 'application/json');

    const params = {
      'id': filter.id
    };

    return this.http.get<Transaction[]>(this.baseUrl, {params, headers});
  }

  save(entity: Transaction): Observable<Transaction> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.put<Transaction>(url, entity, {headers, params});
      // TODO: PUT mapping in backend
    } else {
      return this.http.post<Transaction>(this.baseUrl, entity, {headers, params});
    }
  }

  delete(entity: Transaction): Observable<Transaction> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.delete<Transaction>(url, {headers, params});
    }
    return EMPTY;
  }
}
