import {Component, OnInit, ViewChild} from '@angular/core';
import {STColumn, STComponent} from '@delon/abc/st';
import {SFSchema} from '@delon/form';
import {ModalHelper, _HttpClient} from '@delon/theme';
import {SysUserEditComponent} from './edit/edit.component';

@Component({
  selector: 'app-sys-user',
  templateUrl: './user.component.html',
})
export class SysUserComponent implements OnInit {
  url = `/api/user/list`;
  loading: boolean | null = false;
  searchSchema: SFSchema = {
    properties: {
      account: {
        type: 'string',
        title: '账号'
      },
      nickName: {
        type: 'string',
        title: '昵称'
      }
    }
  };
  @ViewChild('st') private readonly st!: STComponent;
  columns: STColumn[] = [
    {title: '编号', type: 'no'},
    {title: '账号', index: 'account'},
    {title: '昵称', index: 'nickName'},
    {title: '头像', type: 'img', width: '64px', index: 'avatarUrl'},
    {title: '更新人', index: 'lastModifiedNickName'},
    {title: '更新时间', type: 'date', index: 'lastModifiedDate'},
    {
      title: '操作',
      buttons: [
        {text: '编辑', type: 'modal', modal: {component: SysUserEditComponent}, click: 'reload'},
        {text: '删除', type: 'del', click: (item: any) => this.delete(item)},
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit(): void {
  }

  add(): void {
    this.modal
      .createStatic(SysUserEditComponent, {i: {id: 0}})
      .subscribe(() => this.st.reload());
  }


  delete(item: any): void {
    this.loading = true;
    this.http.post(`/api/user/delete/${item.userId}`).subscribe((res: any) => {
      this.loading = null;
      this.st.reload();
    });
  }

}
