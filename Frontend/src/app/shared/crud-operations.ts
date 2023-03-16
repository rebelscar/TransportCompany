import { Observable } from "rxjs";

export interface CrudOperations {
  findById(id: string): Observable<any>;
  load(filter: any): void;
  find(filter: any): Observable<any[]>;
  save(entity: any): Observable<any>;
  delete(entity: any): Observable<any>;
}
