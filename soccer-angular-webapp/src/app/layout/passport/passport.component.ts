import {Component, Inject, OnInit} from '@angular/core';
import {DA_SERVICE_TOKEN, ITokenService} from '@delon/auth';
import {SettingsService} from "@delon/theme";

@Component({
  selector: 'layout-passport',
  templateUrl: './passport.component.html',
  styleUrls: ['./passport.component.less']
})
export class LayoutPassportComponent implements OnInit {
  app: any = {};
  links = [
    {
      title: '帮助',
      href: ''
    },
    {
      title: '隐私',
      href: ''
    },
    {
      title: '条款',
      href: ''
    }
  ];

  constructor(@Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
              private settingsService: SettingsService) {
  }

  ngOnInit(): void {
    this.app = this.settingsService.getApp();
    this.tokenService.clear();
  }
}
