import {Component, OnInit} from '@angular/core';
import {SFSchema, SFUISchema} from '@delon/form';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-sys-role-edit',
  templateUrl: './edit.component.html',
})
export class SysRoleEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      roleValue: {type: 'string', title: '角色标识'},
      roleName: {type: 'string', title: '角色名称'},
    },
    required: ['roleName', 'roleValue'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: {span: 24},
    },
    $roleValue: {
      widget: 'string',
    },
    $roleName: {
      widget: 'string'
    },
  };

  constructor(
    private modal: NzModalRef,
    private msgSrv: NzMessageService,
    public http: _HttpClient,
  ) {
  }

  ngOnInit(): void {
    console.log('init edit', this.record);
    if (this.record.roleId != null) {
      this.http.get(`/api/role/info/${this.record.roleId}`).subscribe(res => (this.i = res.data));
    }
  }

  save(value: any): void {
    const api = this.record.roleId != null ? '/api/role/update' : '/api/role/insert';
    this.http.post(api, value).subscribe(res => {
      this.msgSrv.success(res.msg);
      this.modal.close(true);
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
