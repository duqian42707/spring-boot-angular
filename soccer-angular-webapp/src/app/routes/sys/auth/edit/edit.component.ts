import {Component, OnInit} from '@angular/core';
import {SFSchema, SFSelectWidgetSchema, SFUISchema} from '@delon/form';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';
import {SysAuthService} from "../sys-auth.service";
import {map, Observable} from "rxjs";

@Component({
  selector: 'app-sys-auth-edit',
  templateUrl: './edit.component.html',
})
export class SysAuthEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      authName: {type: 'string', title: '权限名称', maxLength: 15},
      authValue: {type: 'string', title: '权限标识'},
      authFolderId: {
        type: 'string', title: '所属目录', ui: {
          widget: 'select',
          asyncData: () => this.loadAuthFolders()
        } as SFSelectWidgetSchema
      },
    },
    required: ['authName', 'authValue'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: {span: 24},
    },
    $authName: {
      widget: 'string'
    },
  };

  constructor(
    private modal: NzModalRef,
    private msgSrv: NzMessageService,
    private sysAuthService: SysAuthService,
    public http: _HttpClient,
  ) {
  }

  ngOnInit(): void {
    console.log('init edit', this.record);
    if (this.record) {
      this.i = {
        ...this.record
      }
    }
  }

  private loadAuthFolders(): Observable<any> {
    return this.sysAuthService.loadAuthFolders()
      .pipe(
        map(data => data.map((item: any) => ({
            value: item.authFolderId,
            label: item.authFolderName
          }))
        )
      )
  }

  save(value: any): void {
    const api = this.record.authId != null ? '/api/auth/update' : '/api/auth/insert';
    this.http.post(api, value).subscribe(res => {
      this.msgSrv.success(res.msg);
      this.modal.close(true);
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
