import {Component, OnInit} from '@angular/core';
import {SFSchema} from "@delon/form";
import {_HttpClient, ModalHelper} from "@delon/theme";
import {SysDeptEditComponent} from "./edit/edit.component";
import {AuthValue} from "../../../common/auth-value";

export interface TreeNodeInterface {
  deptId: string;
  deptName: string;
  deptCode: string;
  displayIndex: number;
  children?: TreeNodeInterface[];
  level?: number;
  expand?: boolean;
  parent?: TreeNodeInterface;
}

@Component({
  selector: 'app-sys-dept',
  templateUrl: './dept.component.html',
  styles: []
})
export class SysDeptComponent implements OnInit {
  searchSchema: SFSchema = {
    properties: {
      deptName: {
        type: 'string',
        title: '部门名称'
      }
    }
  };

  loading = false;
  listOfMapData: TreeNodeInterface[] = [];
  mapOfExpandedData: { [deptId: string]: TreeNodeInterface[] } = {};

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit(): void {
    this.queryTree();
  }

  queryTree() {
    this.loading = true;
    this.http.get(`/api/dept/all`).subscribe(res => {
      this.listOfMapData = res.data;
      this.listOfMapData.forEach(item => {
        this.mapOfExpandedData[item.deptId] = this.convertTreeToList(item);
      });
      this.loading = false;
    });
  }


  collapse(array: TreeNodeInterface[], data: TreeNodeInterface, $event: boolean): void {
    if (!$event) {
      if (data.children) {
        data.children.forEach(d => {
          const target = array.find(a => a.deptId === d.deptId)!;
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

  visitNode(node: TreeNodeInterface, hashMap: { [deptId: string]: boolean }, array: TreeNodeInterface[]): void {
    if (!hashMap[node.deptId]) {
      hashMap[node.deptId] = true;
      array.push(node);
    }
  }


  add(): void {
    this.modal
      .createStatic(SysDeptEditComponent, {i: {deptId: null}})
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
      .createStatic(SysDeptEditComponent, {i: item})
      .subscribe(() => this.queryTree());
  }

  delete(item: any) {

  }

  protected readonly AuthValue = AuthValue;
}
