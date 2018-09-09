import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient, ModalHelper} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from '@delon/abc';
import {SFSchema} from '@delon/form';
import {SysRoleEditComponent} from "./edit/edit.component";
import {NzMessageService} from "ng-zorro-antd";
import {RoleService} from "@core/service/sys/role.service";

@Component({
  selector: 'app-sys-role',
  templateUrl: './role.component.html',
})
export class SysRoleComponent implements OnInit {
  url = `sys/role/list`;
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
    {title: '编号', type: 'number', index: 'id'},
    {title: '角色名称', index: 'roleName'},
    {
      title: '操作',
      buttons: [
        {text: '查看', click: (item: any) => `/form/${item.id}`},
        {text: '编辑', type: 'static', component: SysRoleEditComponent, click: 'reload'},
        {text: '删除', type: 'del', click: (item: any) => this.delete(item.id)},
      ]
    }
  ];

  constructor(private roleService: RoleService, private modal: ModalHelper, private msg: NzMessageService) {
  }

  ngOnInit() {
  }

  add() {
    this.modal
      .createStatic(SysRoleEditComponent, {i: {id: 0}})
      .subscribe(() => this.st.reload());
  }

  delete(id) {
    this.roleService.delete(id).subscribe((res: any) => {
      this.msg.success(res.msg);
      this.st.reload()
    })
  }

}
