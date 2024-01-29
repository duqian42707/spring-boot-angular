import {Component, OnInit} from '@angular/core';
import {SFSchema, SFUISchema} from '@delon/form';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-sys-dept-edit',
  templateUrl: './edit.component.html',
})
export class SysDeptEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      deptName: {type: 'string', title: '部门名称', maxLength: 50},
      deptCode: {type: 'string', title: '部门编码', maxLength: 50},
      displayIndex: {type: 'number', title: '排序'},
    },
    required: ['deptName', 'deptCode'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: {span: 24},
    },
    $deptName: {
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
  }

  save(value: any): void {
    this.http.post(`/api/dept/update`, value).subscribe(res => {
      this.msgSrv.success(res.msg);
      this.modal.close(true);
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
