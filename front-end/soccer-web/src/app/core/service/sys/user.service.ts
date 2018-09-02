import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {

  }

  save(user: any): Observable<any> {
    return this.http.post(`basic/user/save`, user);
  }
}
