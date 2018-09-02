import { Component, OnInit } from '@angular/core';
import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'app-basic-user-view',
  templateUrl: './view.component.html',
})
export class BasicUserViewComponent implements OnInit {
  record: any = {};
  i: any;

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    public http: _HttpClient
  ) { }

  ngOnInit(): void {
    this.http.get<any>(`basic/user/${this.record.id}`).subscribe(res => {
      this.i = res.data
      if (res.data.gender == '1') this.i.sex = '男';
      if (res.data.gender == '2') this.i.sex = '女';
      this.i.address = [res.data.country, res.data.province, res.data.city].reduce((pre, cur) => {
        return cur ? (pre + ' ' + cur) : pre;
      });
    });
  }

  close() {
    this.modal.destroy(true);
  }
}
