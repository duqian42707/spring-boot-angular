import {Component, OnInit} from '@angular/core';
import {SFSchema, SFUISchema} from '@delon/form';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-sys-menu-edit',
  templateUrl: './edit.component.html',
})
export class SysMenuEditComponent implements OnInit {
  record: any = {};
  i: any;
  schema: SFSchema = {
    properties: {
      menuName: {type: 'string', title: '菜单名称', maxLength: 15},
      menuCode: {type: 'string', title: '菜单编码'},
      link: {type: 'string', title: '链接'},
      externalLink: {type: 'string', title: '外部链接'},
      icon: {type: 'string', title: '图标'},
      displayIndex: {type: 'number', title: '排序'},
    },
    required: ['menuName', 'menuCode'],
  };
  ui: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: {span: 24},
    },
    $menuName: {
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
    console.log(this.record);
    if (this.record.menuId) {
      this.http.get(`/api/menu/info/${this.record.menuId}`).subscribe(res => this.i = res.data);
    }
  }

  save(value: any): void {
    const api = this.record.menuId ? '/api/menu/update' : '/api/menu/insert';
    this.http.post(api, value).subscribe(res => {
      this.msgSrv.success(res.msg);
      this.modal.close(true);
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
