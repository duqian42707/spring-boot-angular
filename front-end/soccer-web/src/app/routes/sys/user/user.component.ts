import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent, SimpleTableData} from '@delon/abc';
import { SFSchema } from '@delon/form';
import {BasicUserEditComponent} from "./edit/edit.component";
import {NzMessageService} from "ng-zorro-antd";
import {BasicUserViewComponent} from "./view/view.component";

@Component({
  selector: 'app-basic-user',
  templateUrl: './user.component.html',
})
export class BasicUserComponent implements OnInit {
  url = `basic/user/list`;
  searchSchema: SFSchema = {
    properties: {
      no: {
        type: 'string',
        title: '编号'
      }
    }
  };
  preDataChange = (data => {
    return data;
  })
  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
    { title: '编号', index: 'id' },
    { title: '头像', type: 'img', width: '80px', index: 'avatarUrl' },
    { title: '账号', index: 'account' },
    { title: '用户名', index: 'userName' },
    { title: '性别', index: 'gender' },
    { title: '最后使用时间',type:'date', index: 'lastLoginTime' },
    {
      title: '操作',
      buttons: [
        { text: '查看', type: 'static', component: BasicUserViewComponent, click: 'reload'},
        { text: '编辑', type: 'static', component: BasicUserEditComponent, click: 'reload' },
        { text: '删除', type: 'del', click: (item: any) => this.delete(item.id)}
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper, private msg: NzMessageService) { }

  ngOnInit() { }

  add() {
    this.modal
      .createStatic(BasicUserEditComponent, { i: { id: 0 } })
      .subscribe(() => this.st.reload());
  }
  delete(id){
    this.http.delete(`basic/user/delete/${id}`).subscribe((res:any)=>{
      this.msg.success(res.msg);
      this.st.reload()
    })
  }

}
