import {Component, Inject, OnInit} from '@angular/core';
import {SFRadioWidgetSchema, SFSchema, SFStringWidgetSchema, SFUISchema} from "@delon/form";
import {_HttpClient} from "@delon/theme";
import {NzMessageService} from "ng-zorro-antd/message";
import {DA_SERVICE_TOKEN, TokenService} from "@delon/auth";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile-safety',
  templateUrl: './profile-safety.component.html',
  styles: []
})
export class ProfileSafetyComponent implements OnInit {
  loading = false;
  safetySchema: SFSchema = {
    properties: {
      oldPassword: {
        type: 'string',
        title: '原密码',
        maxLength: 50,
        ui: {type: 'password'} as SFStringWidgetSchema
      },
      newPassword: {
        type: 'string',
        title: '新密码',
        maxLength: 50,
        ui: {type: 'password'} as SFStringWidgetSchema
      },
      repeatPassword: {
        type: 'string',
        title: '确认密码',
        maxLength: 50,
        ui: {type: 'password'} as SFStringWidgetSchema
      },
    },
    required: ['oldPassword', 'newPassword', 'repeatPassword'],
  };
  safetyUi: SFUISchema = {
    '*': {
      spanLabelFixed: 100,
      grid: {span: 24},
    },
  };
  safetyData = {};

  constructor(public http: _HttpClient, private messageService: NzMessageService,
              @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService, private router: Router) {
  }

  ngOnInit(): void {
  }


  saveSafety(value: any) {
    const data = {
      ...value
    }
    this.http.post('/api/profile/changePassword', data).subscribe(res => {
      this.messageService.success(res.msg);
    })

  }
}
