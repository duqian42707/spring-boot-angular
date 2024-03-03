import {Component, OnInit} from '@angular/core';
import {SFSchema, SFUISchema} from '@delon/form';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';
import {SysDeptService} from "../../dept/sys-dept.service";
import {SFTreeSelectWidgetSchema} from '@delon/form/widgets/tree-select';
import {SFUploadWidgetSchema} from '@delon/form/widgets/upload';

@Component({
  selector: 'app-sys-user-edit',
  templateUrl: './edit.component.html',
})
export class SysUserEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      account: {type: 'string', title: '账号', readOnly: true},
      nickName: {type: 'string', title: '昵称', maxLength: 15},
      avatarUrl: {
        type: 'string', title: '头像', readOnly: true, ui: {
          widget: 'upload',
          action: '/api/file/upload',
          resReName: 'data.url',
          urlReName: 'data.url',
          listType: 'picture-card'
        } as SFUploadWidgetSchema
      },
      deptIds: {
        type: 'string', title: '部门',
        ui: {
          widget: 'tree-select',
          multiple: true,
          asyncData: () => this.sysDeptService.loadDeptTree()
        } as SFTreeSelectWidgetSchema,
      }
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
    private sysDeptService: SysDeptService
  ) {
  }

  ngOnInit(): void {
    console.log(this.record);
    if (this.record.userId) {
      this.http.get(`/api/user/info/${this.record.userId}`).subscribe(res => {
        this.i = res.data
        this.i.deptIds = res.data.depts.map((dept: any) => dept.deptId);
        if (res.data.avatarUrl) {
          this.i.avatarUrl = [{
            uid: -1,
            name: 'xxx.png',
            status: 'done',
            url: res.data.avatarUrl,
            response: {
              fileId: 1
            }
          }]
        }
      });
    }
  }

  save(value: any): void {
    const api = this.record.userId ? '/api/user/update' : '/api/user/insert';
    const data = {
      ...value,
      depts: value.deptIds.map((deptId: string) => ({deptId}))
    };
    this.http.post(api, data).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
