import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient, ModalHelper} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from '@delon/abc';
import {SFSchema} from '@delon/form';
import {SysDictEditComponent} from "./edit/edit.component";
import {DictService} from "@core/service/sys/dict.service";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-sys-dict',
  templateUrl: './dict.component.html',
})
export class SysDictComponent implements OnInit {
  url = `sys/dict/list`;
  searchSchema: SFSchema = {
    properties: {
      no: {
        type: 'string',
        title: '编号'
      }
    }
  };
  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
    {title: '名称', index: 'name'},
    {title: '编码', index: 'code'},
    {title: '值', index: 'value'},
    {title: '显示值', index: 'display'},
    {title: '顺序号', index: 'orderNo'},
    {
      title: '操作',
      buttons: [
        {text: '编辑', type: 'static', click: 'reload'},
        {text: '删除', type: 'del', click: (item: any) => this.delete(item.id)}
      ]
    }
  ];

  constructor(private dictService: DictService,
              private modal: ModalHelper,
              private msgService: NzMessageService) {
  }

  ngOnInit() {
  }

  add() {
    this.modal
      .createStatic(SysDictEditComponent, {i: {id: 0}})
      .subscribe(() => this.st.reload());
  }

  delete(id) {
    this.dictService.delete(id).subscribe(res => {
      this.msgService.success(res.msg);
      this.st.reload();
    })
  }

}
