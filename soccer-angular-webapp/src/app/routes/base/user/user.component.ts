import {Component, OnInit, ViewChild} from '@angular/core';
import {STColumn, STComponent} from '@delon/abc/st';
import {SFSchema} from '@delon/form';
import {ModalHelper, _HttpClient} from '@delon/theme';

@Component({
  selector: 'app-base-user',
  templateUrl: './user.component.html',
})
export class BaseUserComponent implements OnInit {
  url = `/api/user/list`;
  searchSchema: SFSchema = {
    properties: {
      no: {
        type: 'string',
        title: '编号'
      }
    }
  };
  @ViewChild('st') private readonly st!: STComponent;
  columns: STColumn[] = [
    {title: '编号', type: 'no'},
    {title: '账号', index: 'account'},
    {title: '昵称', index: 'nickName'},
    {title: '头像', type: 'img', width: '64px', index: 'avatarUrl'},
    {title: '时间', type: 'date', index: 'updatedAt'},
    {
      title: '操作',
      buttons: [
        // {text: '编辑', type: 'static', component: FormEditComponent, click: 'reload'},
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit(): void {
  }

  add(): void {
    // this.modal
    //   .createStatic(FormEditComponent, { i: { id: 0 } })
    //   .subscribe(() => this.st.reload());
  }

}
