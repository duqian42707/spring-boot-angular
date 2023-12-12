import {Injectable} from '@angular/core';
import {_HttpClient} from '@delon/theme';
import {map, Observable} from "rxjs";

@Injectable()
export class SysMenuService {

  constructor(private http: _HttpClient) {
  }

  loadMenuAuthTree(): Observable<any> {
    return this.http.get('/api/menu/tree').pipe(
      map(res => this.buildTreeNodes(res.data, 'menu'))
    )
  }

  buildTreeNodes(data: Array<any>, type: 'menu' | 'auth'): Array<any> {
    if (data == null) {
      return [];
    }
    if (type == 'menu') {
      return data.map(x => {
        const childrenMenu = this.buildTreeNodes(x.children, 'menu');
        const childrenAuth = this.buildTreeNodes(x.auths, 'auth');
        const children = [...childrenMenu, ...childrenAuth]
        return {...x, type, key: x.menuId, title: x.menuName, children, isLeaf: children.length == 0}
      })
    } else {
      return data.map(x => {
        return {...x, type, key: x.authId, title: x.authName, children: [], isLeaf: true}
      })
    }
  }

}
