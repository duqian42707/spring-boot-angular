import {Component, OnInit} from '@angular/core';
import {SFSchema, SFTreeSelectWidgetSchema, SFUISchema} from '@delon/form';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';
import {SysDeptService} from "../sys-dept.service";

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
      parentId: {
        type: 'string', title: '上级部门',
        ui: {
          widget: 'tree-select',
          asyncData: () => this.sysDeptService.loadDeptTree()
        } as SFTreeSelectWidgetSchema,
      },
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
    private sysDeptService: SysDeptService
  ) {
  }

  ngOnInit(): void {
    console.log(this.record);
    if (this.record.deptId) {
      this.http.get(`/api/dept/info/${this.record.deptId}`).subscribe(res => this.i = res.data);
    }
  }

  save(value: any): void {
    const api = this.record.deptId ? '/api/dept/update' : '/api/dept/insert';
    this.http.post(api, value).subscribe(res => {
      this.msgSrv.success(res.msg);
      this.modal.close(true);
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
