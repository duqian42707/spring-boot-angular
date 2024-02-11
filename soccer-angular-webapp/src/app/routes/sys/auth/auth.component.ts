import {Component, ViewChild} from '@angular/core';
import {SFSchema} from "@delon/form";
import {STColumn, STComponent} from "@delon/abc/st";
import {formatUsername} from "../../../shared/utils/format-username";
import {_HttpClient, DrawerHelper, ModalHelper} from "@delon/theme";
import {SysAuthEditComponent} from "./edit/edit.component";
import {AuthValue} from "../../../common/auth-value";
import {ACLService} from "@delon/acl";

@Component({
  selector: 'app-sys-auth',
  templateUrl: './auth.component.html',
  styles: []
})
export class SysAuthComponent {
  loading: boolean | null = null;
  url = `/api/auth/list`;
  searchSchema: SFSchema = {
    properties: {
      authName: {
        type: 'string',
        title: '权限名称'
      },
      authValue: {
        type: 'string',
        title: '权限标识'
      },
      authFolderName: {
        type: 'string',
        title: '所属目录'
      }
    }
  };
  @ViewChild('st') private readonly st!: STComponent;
  columns: STColumn[] = [
    {title: '编号', type: 'no'},
    {title: '权限名称', index: 'authName'},
    {title: '权限标识', index: 'authValue'},
    {title: '所属目录', index: 'authFolderName'},
    {title: '更新人', index: 'lastModifiedNickName'},
    {title: '更新时间', type: 'date', index: 'lastModifiedDate'},
    {
      title: '操作',
      buttons: [
        {
          text: '编辑',
          type: 'modal',
          modal: {component: SysAuthEditComponent},
          click: 'reload',
          iif: () => this.aclService.can(AuthValue.SYS_AUTH_UPDATE)
        },
        {
          text: '删除',
          type: 'del',
          click: (item: any) => this.delete(item),
          iif: () => this.aclService.can(AuthValue.SYS_AUTH_DELETE)
        },
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper, private drawerHelper: DrawerHelper, private aclService: ACLService) {
  }

  ngOnInit(): void {
  }

  add(): void {
    this.modal
      .createStatic(SysAuthEditComponent, {i: {authId: null}})
      .subscribe(() => this.st.reload());
  }

  delete(item: any): void {
    this.loading = true;
    this.http.post(`/api/auth/delete/${item.authId}`).subscribe((res: any) => {
      this.loading = null;
      this.st.reload();
    });
  }

  protected readonly AuthValue = AuthValue;
}
