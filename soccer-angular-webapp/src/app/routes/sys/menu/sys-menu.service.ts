import {Injectable} from '@angular/core';
import {_HttpClient} from '@delon/theme';
import {map, Observable} from "rxjs";

@Injectable()
export class SysMenuService {

  constructor(private http: _HttpClient) {
  }

  loadMenuTree(): Observable<any> {
    return this.http.get('/api/menu/tree').pipe(
      map(res => res.data)
    )
  }


}
