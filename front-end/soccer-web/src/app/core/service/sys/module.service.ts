import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ModuleService {

  constructor(private http: HttpClient) {
  }

  getList(): Observable<any> {
    return this.http.get(`sys/module/list`);
  }

  getOne(id: number): Observable<any> {
    return this.http.get(`sys/module/get/${id}`).pipe(map((res: any) => res.data))
  }

  save(obj: any): Observable<any> {
    return this.http.post(`sys/module/save`, obj);
  }

}
