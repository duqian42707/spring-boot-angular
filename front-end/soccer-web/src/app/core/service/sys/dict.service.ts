import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class DictService {

  constructor(private http: HttpClient) {
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`sys/dict/delete/${id}`);
  }
}
