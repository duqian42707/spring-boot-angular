import {Injectable} from '@angular/core';
import {_HttpClient} from '@delon/theme';
import {map, Observable} from "rxjs";

@Injectable()
export class SysDeptService {

  constructor(private http: _HttpClient) {
  }

  loadDeptTree(): Observable<any> {
    return this.http.get('/api/dept/tree').pipe(
      map(res => res.data)
    )
  }


}
