import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {

  }

  get(id: any): Observable<any> {
    return this.http.get<any>(`basic/user/get/${id}`)
  }

  save(user: any): Observable<any> {
    return this.http.post(`basic/user/save`, user);
  }
}
