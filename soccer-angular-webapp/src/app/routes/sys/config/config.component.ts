import {Component, Inject, OnInit} from '@angular/core';
import {_HttpClient} from "@delon/theme";
import {SFSchema, SFSelectWidgetSchema, SFUISchema} from "@delon/form";
import {NzMessageService} from "ng-zorro-antd/message";
import {DA_SERVICE_TOKEN, TokenService} from "@delon/auth";
import {Router} from "@angular/router";
import {ACLService} from "@delon/acl";
import {AuthValue} from "../../../common/auth-value";

@Component({
  selector: 'app-sys-config',
  templateUrl: './config.component.html',
  styles: []
})
export class SysConfigComponent implements OnInit {
  loading = false;
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
  canSaveConfig = false;
  canReInitData = false;

  constructor(public http: _HttpClient, private messageService: NzMessageService,
              @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService, private router: Router,
              private aclService: ACLService) {
  }

  ngOnInit(): void {
    this.loadConfig();
    this.canSaveConfig = this.aclService.can(AuthValue.SYS_CONFIG_SET);
    this.canReInitData = this.aclService.can(AuthValue.SYS_RE_INIT_DATA);
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

  reInitData() {
    this.loading = true;
    this.http.post('/api/sys/reInitData').subscribe({
      next: res => {
        const nzMessageRef = this.messageService.success(res.msg + "  系统即将退出登录");
        this.loading = false;
        nzMessageRef.onClose.subscribe(res => {
          this.tokenService.clear();
          this.router.navigateByUrl(this.tokenService.login_url!);
        })

      },
      error: (err) => {
        this.loading = false;
      }
    })

  }
}
