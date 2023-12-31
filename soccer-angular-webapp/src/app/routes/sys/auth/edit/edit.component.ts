import {Component, OnInit} from '@angular/core';
import {SFSchema, SFUISchema} from '@delon/form';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';

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
      authFolderId: {type: 'string', title: '所属目录'},
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

  save(value: any): void {
    this.http.post(`/api/auth/update`, value).subscribe(res => {
      this.msgSrv.success(res.msg);
      this.modal.close(true);
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
