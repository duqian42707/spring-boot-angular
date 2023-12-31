import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient} from '@delon/theme';
import {NzDrawerRef} from "ng-zorro-antd/drawer";
import {NzTreeComponent} from "ng-zorro-antd/tree";
import {NzMessageService} from "ng-zorro-antd/message";
import {SysAuthService} from "../../auth/sys-auth.service";

@Component({
  selector: 'app-sys-role-auth',
  templateUrl: './auth.component.html',
})
export class SysRoleAuthComponent implements OnInit {
  record: any = {};
  treeNodes = [];
  defaultCheckedKeys: string[] = [];
  loading = false;
  saveLoading = false;
  @ViewChild('treeComponent') private treeComponent!: NzTreeComponent

  constructor(public http: _HttpClient, private nzDrawerRef: NzDrawerRef,
              private nzMessageService: NzMessageService, private sysAuthService: SysAuthService) {
  }

  ngOnInit(): void {
    console.log(this.record)
    this.loadAuthTree();
    this.loadRoleInfo();
  }

  loadRoleInfo() {
    this.loading = true;
    this.http.get('/api/role/info/' + this.record.roleId).subscribe(res => {
      this.loading = false;
      this.defaultCheckedKeys = res.data.auths.map((x: any) => x.authId);
    })
  }

  loadAuthTree() {
    this.sysAuthService.loadAuthTree().subscribe(data => {
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
    const authIds: string[] = [];
    nodeList.forEach(node => {
      authIds.push(node.key);
    });
    const param = {
      roleId: this.record.roleId,
      auths: authIds.map(authId => ({authId})),
    }
    this.saveLoading = true;
    this.http.post('/api/role/saveRoleAuth', param).subscribe(res => {
      this.saveLoading = false;
      this.nzMessageService.success(res.msg);
      this.nzDrawerRef.close();
    })
  }
}
