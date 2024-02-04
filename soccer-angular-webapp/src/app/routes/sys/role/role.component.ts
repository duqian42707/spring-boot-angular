import {Component, OnInit, ViewChild} from '@angular/core';
import {STColumn, STComponent} from '@delon/abc/st';
import {SFSchema} from '@delon/form';
import {ModalHelper, _HttpClient, DrawerHelper} from '@delon/theme';
import {SysRoleEditComponent} from './edit/edit.component';
import {formatUsername} from '../../../shared/utils/format-username';
import {SysRoleMenuComponent} from "./menu/menu.component";
import {SysRoleAuthComponent} from "./auth/auth.component";
import {ACLService} from "@delon/acl";

@Component({
  selector: 'app-sys-role',
  templateUrl: './role.component.html',
})
export class SysRoleComponent implements OnInit {
  loading: boolean | null = null;
  url = `/api/role/list`;
  searchSchema: SFSchema = {
    properties: {
      roleName: {
        type: 'string',
        title: '角色名称'
      },
      roleValue: {
        type: 'string',
        title: '角色标识'
      }
    }
  };
  @ViewChild('st') private readonly st!: STComponent;
  columns: STColumn[] = [
    {title: '编号', type: 'no'},
    {title: '角色标识', index: 'roleValue'},
    {title: '角色名称', index: 'roleName'},
    {title: '更新人', index: 'lastModifiedNickName'},
    {title: '更新时间', type: 'date', index: 'lastModifiedDate'},
    {
      title: '操作',
      buttons: [
        {
          text: '编辑',
          type: 'modal',
          modal: {component: SysRoleEditComponent},
          click: 'reload',
          iif: () => this.aclService.can('sys_role_update')
        },
        {
          text: '删除',
          type: 'del',
          click: (item: any) => this.delete(item),
          iif: () => this.aclService.can('sys_role_delete')
        },
        {
          text: '配置菜单',
          type: 'drawer',
          drawer: {component: SysRoleMenuComponent, drawerOptions: {nzClosable: false}},
          click: 'reload',
          iif: () => this.aclService.can('sys_role_update')
        },
        {
          text: '配置权限',
          type: 'drawer',
          drawer: {component: SysRoleAuthComponent, drawerOptions: {nzClosable: false}},
          click: 'reload',
          iif: () => this.aclService.can('sys_role_update')
        },
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper, private aclService: ACLService) {
  }

  ngOnInit(): void {
  }

  add(): void {
    this.modal
      .createStatic(SysRoleEditComponent, {i: {roleId: null}})
      .subscribe(() => this.st.reload());
  }

  delete(item: any): void {
    this.loading = true;
    this.http.post(`/api/role/delete/${item.roleId}`).subscribe((res: any) => {
      this.loading = null;
      this.st.reload();
    });
  }
}
