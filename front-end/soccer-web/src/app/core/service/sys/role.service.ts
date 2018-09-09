import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class RoleService {

  constructor(private http: HttpClient) {

  }

  get(id: any): Observable<any> {
    return this.http.get(`sys/role/get/${id}`);
  }

  save(value): Observable<any> {
    return this.http.post(`sys/role/save`, value);
  }

  getRoleList(): Observable<any> {
    return this.http.get(`sys/role/list`);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`sys/role/delete/${id}`);
  }
}
