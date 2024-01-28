import {Component, Inject, OnInit} from '@angular/core';
import {SFRadioWidgetSchema, SFSchema, SFUISchema} from "@delon/form";
import {_HttpClient} from "@delon/theme";
import {NzMessageService} from "ng-zorro-antd/message";
import {DA_SERVICE_TOKEN, TokenService} from "@delon/auth";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile-basic',
  templateUrl: './profile-basic.component.html',
  styles: []
})
export class ProfileBasicComponent implements OnInit {
  loading = false;
  basicSchema: SFSchema = {
    properties: {
      nickName: {type: 'string', title: '昵称', maxLength: 50},
      avatarUrl: {type: 'string', title: '头像', maxLength: 500},
      gender: {
        type: 'string',
        title: '性别',
        ui: {widget: "radio"} as SFRadioWidgetSchema,
        enum: [{value: 1, label: '男'}, {value: 2, label: '女'}, {value: 0, label: '保密'}]
      },
    },
    required: ['nickName'],
  };
  basicUi: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: {span: 24},
    },
  };
  basicData = {};

  constructor(public http: _HttpClient, private messageService: NzMessageService,
              @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadBasic();
  }

  loadBasic() {
    this.http.get('/api/profile/userInfo').subscribe(res => {
      this.basicData = res.data;
    })
  }

  saveBasic(value: any) {
    const data = {
      ...value
    }
    this.http.post('/api/profile/updateProfile', data).subscribe(res => {
      this.messageService.success(res.msg);
    })

  }
}
