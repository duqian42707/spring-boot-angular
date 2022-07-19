import {Component, OnInit} from '@angular/core';
import {SFSchema} from '@delon/form';
import {_HttpClient, ModalHelper} from '@delon/theme';
import {SysMenuEditComponent} from './edit/edit.component';

export interface TreeNodeInterface {
  menuId: string;
  menuName: string;
  menuCode: string;
  link: string;
  icon: string;
  children?: TreeNodeInterface[];
  level?: number;
  expand?: boolean;
  parent?: TreeNodeInterface;
}

@Component({
  selector: 'app-sys-menu',
  templateUrl: './menu.component.html',
})
export class SysMenuComponent implements OnInit {
  url = `/user`;
  searchSchema: SFSchema = {
    properties: {
      no: {
        type: 'string',
        title: '编号'
      }
    }
  };

  loading = false;
  listOfMapData: TreeNodeInterface[] = [];
  mapOfExpandedData: { [menuId: string]: TreeNodeInterface[] } = {};

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit(): void {
    this.queryTree();
  }

  queryTree() {
    this.loading = true;
    this.http.get(`/api/menu/tree`).subscribe(res => {
      this.listOfMapData = res.data;
      this.listOfMapData.forEach(item => {
        this.mapOfExpandedData[item.menuId] = this.convertTreeToList(item);
      });
      this.loading = false;
    });
  }


  collapse(array: TreeNodeInterface[], data: TreeNodeInterface, $event: boolean): void {
    if (!$event) {
      if (data.children) {
        data.children.forEach(d => {
          const target = array.find(a => a.menuId === d.menuId)!;
          target.expand = false;
          this.collapse(array, target, false);
        });
      } else {
        return;
      }
    }
  }

  convertTreeToList(root: TreeNodeInterface): TreeNodeInterface[] {
    const stack: TreeNodeInterface[] = [];
    const array: TreeNodeInterface[] = [];
    const hashMap = {};
    stack.push({...root, level: 0, expand: false});

    while (stack.length !== 0) {
      const node = stack.pop()!;
      this.visitNode(node, hashMap, array);
      if (node.children) {
        for (let i = node.children.length - 1; i >= 0; i--) {
          stack.push({...node.children[i], level: node.level! + 1, expand: false, parent: node});
        }
      }
    }

    return array;
  }

  visitNode(node: TreeNodeInterface, hashMap: { [menuId: string]: boolean }, array: TreeNodeInterface[]): void {
    if (!hashMap[node.menuId]) {
      hashMap[node.menuId] = true;
      array.push(node);
    }
  }


  add(): void {
    this.modal
      .createStatic(SysMenuEditComponent, {i: {menuId: null}})
      .subscribe(() => this.queryTree());
  }

  search(evt: any) {
    this.queryTree();
  }

  reset(evt: any) {
    this.queryTree();
  }

  edit(item: any) {
    this.modal
      .createStatic(SysMenuEditComponent, {i: item})
      .subscribe(() => this.queryTree());
  }

  delete(item: any) {

  }

}
