import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient, DrawerHelper} from '@delon/theme';
import {SysMenuService} from "../../menu/sys-menu.service";
import {NzDrawerRef} from "ng-zorro-antd/drawer";
import {NzTreeComponent} from "ng-zorro-antd/tree";
import {NzMessageService} from "ng-zorro-antd/message";
import {NzTreeNode} from "ng-zorro-antd/core/tree/nz-tree-base-node";
import {getCheckedNodes} from "../../../../shared/utils/treeUtils";

@Component({
  selector: 'app-sys-role-menu',
  templateUrl: './menu.component.html',
})
export class SysRoleMenuComponent implements OnInit {
  record: any = {};
  treeNodes = [];
  defaultCheckedKeys: string[] = [];
  loading = false;
  saveLoading = false;
  @ViewChild('treeComponent') private treeComponent!: NzTreeComponent

  constructor(public http: _HttpClient, private nzDrawerRef: NzDrawerRef,
              private nzMessageService: NzMessageService, private sysMenuService: SysMenuService) {
  }

  ngOnInit(): void {
    console.log(this.record)
    this.loadMenuTree();
    this.loadRoleInfo();
  }

  loadRoleInfo() {
    this.loading = true;
    this.http.get('/api/role/info/' + this.record.roleId).subscribe(res => {
      this.loading = false;
      this.defaultCheckedKeys = res.data.menus.map((x: any) => x.menuId);
    })
  }

  loadMenuTree() {
    this.sysMenuService.loadMenuTree().subscribe(data => {
      this.treeNodes = data;
    })
  }

  cancel(): void {
    this.nzDrawerRef.close();
  }

  ok(): void {
    const nodeList: NzTreeNode[] = getCheckedNodes(this.treeComponent);
    const menuIds: string[] = [];
    nodeList.forEach(node => {
      menuIds.push(node.key);
    });
    const param = {
      roleId: this.record.roleId,
      menus: menuIds.map(menuId => ({menuId})),
    }
    this.saveLoading = true;
    this.http.post('/api/role/saveRoleMenu', param).subscribe(res => {
      this.saveLoading = false;
      this.nzMessageService.success(res.msg);
      this.nzDrawerRef.close();
    })
  }
}
