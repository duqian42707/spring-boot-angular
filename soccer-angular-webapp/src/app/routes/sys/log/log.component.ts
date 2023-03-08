import {Component, OnInit, ViewChild} from '@angular/core';
import {STColumn, STComponent} from '@delon/abc/st';
import {SFSchema} from '@delon/form';
import {ModalHelper, _HttpClient} from '@delon/theme';

@Component({
  selector: 'app-sys-log',
  templateUrl: './log.component.html',
})
export class SysLogComponent implements OnInit {
  url = `/api/log/list`;
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
    {title: '账号', index: 'username'},
    {title: '昵称', index: 'nickName'},
    {title: '请求地址', index: 'requestUrl'},
    {title: '耗时', type: 'number', index: 'runTime'},
    {title: '时间', type: 'date', index: 'createdDate'},
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
