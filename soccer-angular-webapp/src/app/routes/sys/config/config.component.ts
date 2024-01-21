import {Component, OnInit} from '@angular/core';
import {_HttpClient} from "@delon/theme";
import {SFSchema, SFSelectWidgetSchema, SFUISchema} from "@delon/form";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-sys-config',
  templateUrl: './config.component.html',
  styles: []
})
export class SysConfigComponent implements OnInit {

  basicSchema: SFSchema = {
    properties: {
      sys_name: {type: 'string', title: '系统名称', maxLength: 15},
      sys_desc: {type: 'string', title: '系统描述', ui: {widget: 'textarea'}, maxLength: 500},
    },
    required: ['sys_name'],
  };
  basicUi: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: {span: 24},
    },
  };

  basicData = {};

  constructor(public http: _HttpClient, private messageService: NzMessageService) {
  }

  ngOnInit(): void {
    this.loadConfig();

  }


  loadConfig() {
    this.http.get('/api/config/allMap').subscribe(res => {
      this.basicData = {
        sys_name: res.data.sys_name,
        sys_desc: res.data.sys_desc,
      };
    })
  }

  saveBasic(value: any) {
    this.http.post('/api/config/set', value).subscribe(res => {
      this.messageService.success(res.msg);
      this.loadConfig();
    })
  }
}
