import { Injectable } from '@angular/core';
import { Employee } from "./employee.model";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { CrudOperations } from "../shared/crud-operations";
import { EMPTY, Observable } from "rxjs";
import { FromToFilter } from "../shared/from-to-filter";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService implements CrudOperations {

  baseUrl = 'http://localhost:8080/employees';
  employeeList: Employee[] = [];

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Employee> {
    const url = `${this.baseUrl}/${id}`;
    const params = { 'id': id };
    const headers = new HttpHeaders().set('Accept', 'application/json');
    return this.http.get<Employee>(url, {params, headers});
  }

  load(filter: FromToFilter): void {
    this.find(filter).subscribe(result => {
        this.employeeList = result;
      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(filter: FromToFilter): Observable<Employee[]> {
    const headers = new HttpHeaders().set('Accept', 'application/json');

    const params = {
      'from': filter.from,
      'to': filter.to,
    };

    return this.http.get<Employee[]>(this.baseUrl, {params, headers});
  }

  save(entity: Employee): Observable<Employee> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.put<Employee>(url, entity, {headers, params});
      // TODO: PUT mapping in backend
    } else {
      return this.http.post<Employee>(this.baseUrl, entity, {headers, params});
    }
  }

  delete(entity: Employee): Observable<Employee> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.delete<Employee>(url, {headers, params});
    }
    return EMPTY;
  }
}
