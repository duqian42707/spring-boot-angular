import {Injectable} from '@angular/core';
import {_HttpClient} from '@delon/theme';
import {map, Observable} from "rxjs";

@Injectable()
export class SysAuthService {

  constructor(private http: _HttpClient) {
  }

  loadAuthFolders(): Observable<any> {
    return this.http.get('/api/auth/folders').pipe(
      map(res => res.data)
    )
  }

  loadAuthTree(): Observable<any> {
    return this.http.get('/api/auth/tree').pipe(
      map(res => res.data)
    )
  }


}
