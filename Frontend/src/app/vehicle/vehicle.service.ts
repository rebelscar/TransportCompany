import { Injectable } from '@angular/core';
import { CrudOperations } from "../shared/crud-operations";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { EMPTY, Observable } from "rxjs";
import { Vehicle } from "./vehicle.model";
import { BrandFilter } from "./brand-filter";

@Injectable({
  providedIn: 'root'
})
export class VehicleService implements CrudOperations {

  baseUrl = 'http://localhost:8080/vehicles';
  vehicleList: Vehicle[] = [];

  constructor(private http: HttpClient) {
  }

  findById(id: string): Observable<Vehicle> {
    const url = `${this.baseUrl}/${id}`;
    const params = { 'id': id };
    const headers = new HttpHeaders().set('Accept', 'application/json');

    return this.http.get<Vehicle>(url, {params, headers});
  }

  load(filter: BrandFilter): void {
    this.find(filter).subscribe(result => {
        this.vehicleList = result;
      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(filter: BrandFilter): Observable<Vehicle[]> {
    const headers = new HttpHeaders().set('Accept', 'application/json');

    const params = {
      'brand': filter.brand
    };

    return this.http.get<Vehicle[]>(this.baseUrl, {params, headers});
  }

  save(entity: Vehicle): Observable<Vehicle> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.put<Vehicle>(url, entity, {headers, params});
      // TODO: PUT mapping in backend
    } else {
      return this.http.post<Vehicle>(this.baseUrl, entity, {headers, params});
    }
  }

  delete(entity: Vehicle): Observable<Vehicle> {
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');

    if (entity.id) {
      url = `${this.baseUrl}/${entity.id.toString()}`;
      params = new HttpParams().set('ID', entity.id.toString());
      return this.http.delete<Vehicle>(url, {headers, params});
    }
    return EMPTY;
  }
}
