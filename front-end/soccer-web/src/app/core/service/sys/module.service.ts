import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {NzTreeNode} from "ng-zorro-antd";

@Injectable({
  providedIn: 'root'
})
export class ModuleService {


  constructor(private http: HttpClient) {
  }

  getList(): Observable<any> {
    return this.http.get(`sys/module/list`);
  }

  getMenuForTreeNode(): Observable<NzTreeNode[]> {
    return this.http.get(`sys/module/list`).pipe(map((res: any) => {
      let list = [];
      if (res.data) {
        list = res.data.map(v => this.menu2NzTreeNode(v));
      }
      return list;
    }));
  }

  getOne(id: number): Observable<any> {
    return this.http.get(`sys/module/get/${id}`).pipe(map((res: any) => res.data))
  }

  save(obj: any): Observable<any> {
    return this.http.post(`sys/module/save`, obj);
  }

  delete(id): Observable<any> {
    return this.http.delete(`sys/module/delete/${id}`);
  }


  private menu2NzTreeNode(menu: any) {
    let children = null;
    if (menu.children) {
      children = menu.children.map(v => this.menu2NzTreeNode(v));
    }
    return new NzTreeNode({
      key: menu.id,
      title: menu.name,
      children: children,
    })
  }

}
