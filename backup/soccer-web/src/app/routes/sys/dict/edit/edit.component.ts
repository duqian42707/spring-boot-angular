import {Component, OnInit, ViewChild} from '@angular/core';
import {NzModalRef, NzMessageService} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {SFSchema, SFUISchema} from '@delon/form';

@Component({
  selector: 'app-sys-dict-edit',
  templateUrl: './edit.component.html',
})
export class SysDictEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      name: {type: 'string', title: '名称', maxLength: 15},
      code: {type: 'string', title: '编码'},
      value: {type: 'string', title: '值'},
      display: {type: 'string', title: '显示值'},
      orderNo: {type: 'number', title: '顺序号'},
    },
    required: ['name', 'code', 'value', 'display'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      autocomplete:'off',
      // grid: {span: 12},
    },
  };

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    public http: _HttpClient,
  ) {
  }

  ngOnInit(): void {
    if (this.record.id > 0) {
      this.http.get(`sys/dict/get/${this.record.id}`).subscribe(res => (this.i = res));
    }
  }

  save(value: any) {
    if (value.id == 0) {
      value.id = null;
    }
    this.http.post(`sys/dict/save`, value).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close() {
    this.modal.destroy();
  }
}
