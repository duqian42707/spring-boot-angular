import {Component, OnInit} from '@angular/core';
import {SFSchema, SFUISchema} from '@delon/form';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-sys-user-edit',
  templateUrl: './edit.component.html',
})
export class SysUserEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      account: {type: 'string', title: '账号'},
      nickName: {type: 'string', title: '昵称', maxLength: 15},
      avatarUrl: {type: 'number', title: '头像'},
    },
    required: ['account', 'nickName'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: {span: 12},
    },
  };

  constructor(
    private modal: NzModalRef,
    private msgSrv: NzMessageService,
    public http: _HttpClient,
  ) {
  }

  ngOnInit(): void {
    console.log(this.record);
    if (this.record.userId) {
      this.http.get(`/api/user/info/${this.record.userId}`).subscribe(res => this.i = res.data);
    }
  }

  save(value: any): void {
    const api = this.record.userId ? '/api/user/update' : '/api/user/insert';
    this.http.post(api, value).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
