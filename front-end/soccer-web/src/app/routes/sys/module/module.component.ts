import {Component, OnInit} from '@angular/core';
import {ModalHelper} from '@delon/theme';
import {ModuleService} from "@core/service/sys/module.service";
import {SysModuleEditComponent} from "./edit/edit.component";

export interface TreeNodeInterface {
  id: number;
  name: string;
  level: number;
  expand: boolean;
  children?: TreeNodeInterface[];
}

@Component({
  selector: 'app-sys-module',
  templateUrl: './module.component.html',
})
export class SysModuleComponent implements OnInit {
  url = `sys/module/list`;
  data = [];
  expandDataCache = {};

  constructor(private moduleService: ModuleService, private modal: ModalHelper) {
  }

  collapse(array: TreeNodeInterface[], data: TreeNodeInterface, $event: boolean): void {
    if ($event === false) {
      if (data.children) {
        data.children.forEach(d => {
          const target = array.find(a => a.id === d.id);
          target.expand = false;
          this.collapse(array, target, false);
        });
      } else {
        return;
      }
    }
  }

  convertTreeToList(root: object): TreeNodeInterface[] {
    const stack = [];
    const array = [];
    const hashMap = {};
    stack.push({...root, level: 0, expand: false});

    while (stack.length !== 0) {
      const node = stack.pop();
      this.visitNode(node, hashMap, array);
      if (node.children) {
        for (let i = node.children.length - 1; i >= 0; i--) {
          stack.push({...node.children[i], level: node.level + 1, expand: false, parent: node});
        }
      }
    }

    return array;
  }

  visitNode(node: TreeNodeInterface, hashMap: object, array: TreeNodeInterface[]): void {
    if (!hashMap[node.id]) {
      hashMap[node.id] = true;
      array.push(node);
    }
  }

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.moduleService.getList().subscribe(res => {
      this.data = res.data;
      this.data.forEach((item: any) => {
        this.expandDataCache[item.id] = this.convertTreeToList(item);
      });
      console.log(this.expandDataCache)
    })

  }

  add(parentId?: number) {
    this.modal
      .createStatic(SysModuleEditComponent, {i: {id: 0, parentId: parentId}})
      .subscribe(() => this.loadData());
  }

  view(id) {
    console.log('view', id)
  }

  edit(id) {
    console.log('edit', id)
    this.modal
      .createStatic(SysModuleEditComponent, {record: {id: id}})
      .subscribe(() => this.loadData());
  }

  delete(id) {
    console.log('delete', id)

  }


}
