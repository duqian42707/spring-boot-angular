import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient, DrawerHelper} from '@delon/theme';
import {SysMenuService} from "../../menu/sys-menu.service";
import {NzDrawerRef} from "ng-zorro-antd/drawer";
import {NzTreeComponent} from "ng-zorro-antd/tree";
import {NzMessageService} from "ng-zorro-antd/message";

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
    this.loadMenuAuthTree();
    this.loadRoleInfo();
  }

  loadRoleInfo() {
    this.loading = true;
    this.http.get('/api/role/info/' + this.record.roleId).subscribe(res => {
      this.loading = false;
      const menuIds = res.data.menus.map((x: any) => x.menuId);
      const authIds = res.data.auths.map((x: any) => x.authId);
      this.defaultCheckedKeys = [...menuIds, ...authIds];
    })
  }

  loadMenuAuthTree() {
    this.sysMenuService.loadMenuAuthTree().subscribe(data => {
      this.treeNodes = data;
    })
  }

  cancel(): void {
    this.nzDrawerRef.close();
  }

  ok(): void {
    const checkedNodeList = this.treeComponent.getCheckedNodeList();
    const halfCheckedNodeList = this.treeComponent.getHalfCheckedNodeList();
    const nodeList = [...checkedNodeList, ...halfCheckedNodeList];
    const menuIds: string[] = [];
    const authIds: string[] = [];
    nodeList.forEach(node => {
      if (node.origin['type'] == 'menu') {
        menuIds.push(node.key);
      } else if (node.origin['type'] == 'auth') {
        authIds.push(node.key);
      }
    });
    const param = {
      roleId: this.record.roleId,
      menus: menuIds.map(menuId => ({menuId})),
      auths: authIds.map(authId => ({authId})),
    }
    this.saveLoading = true;
    this.http.post('/api/role/saveRoleMenuAuth', param).subscribe(res => {
      this.saveLoading = false;
      this.nzMessageService.success(res.msg);
      this.nzDrawerRef.close();
    })
  }
}
