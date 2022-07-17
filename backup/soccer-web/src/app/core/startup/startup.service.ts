import {Injectable, Injector, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {zip} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {MenuService, SettingsService, TitleService, ALAIN_I18N_TOKEN} from '@delon/theme';
import {DA_SERVICE_TOKEN, ITokenService} from '@delon/auth';
import {ACLService} from '@delon/acl';

/**
 * 用于应用启动时
 * 一般用来获取应用所需要的基础数据等
 */
@Injectable()
export class StartupService {
  constructor(
    private menuService: MenuService,
    private settingService: SettingsService,
    private aclService: ACLService,
    private titleService: TitleService,
    @Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
    private httpClient: HttpClient,
    private injector: Injector
  ) {
  }

  private viaHttp(resolve: any, reject: any) {
    //刷新token
    this.httpClient.get(`refresh`).subscribe((res: any) => {
      this.tokenService.set({
        token: res.token
      })
    });

    zip(
      this.httpClient.get('sys/frame/info')
    ).pipe(
      // 接收其他拦截器后产生的异常消息
      catchError(([appData]) => {
        resolve(null);
        if (!appData) appData = {}
        return [appData];
      })
    ).subscribe(([appData]) => {
        const res: any = appData;
        if (!appData) {
          return false;
        }
        const app: any = {
          name: `soccer`,
          description: `基于Spring boot + angular 的前后端分离的后台管理系统。`
        };
        const user = res.user;
        user.name = user.userName;
        user.avatar = user.avatarUrl;
        // application data
        // 应用信息：包括站点名、描述、年份
        this.settingService.setApp(app);
        // 用户信息：包括姓名、头像、邮箱地址
        this.settingService.setUser(user);
        // ACL：设置权限为全量
        this.aclService.setFull(true);
        // 初始化菜单
        let menu = this.formatMenu(res.menu);
        menu = [{
          text: '主菜单',
          children: [
            {
              text: '仪表盘',
              link: '/dashboard',
              icon: 'anticon anticon-appstore-o',
            }, {
              text: '个人资料',
              link: '/sys/profile',
              icon: 'anticon anticon-user',
            }
          ]
        },
          ...menu];
        this.menuService.add(menu);
        // 设置页面标题的后缀
        this.titleService.suffix = app.name;
      },
      () => {
      },
      () => {
        resolve(null);
      });
  }

  private formatMenu(nodeList) {
    return nodeList.map(node => {
      node.text = node.name;
      node.link = node.url;
      node.children = this.formatMenu(node.children);
      return node;
    })
  }

  private viaMock(resolve: any, reject: any) {
    // const tokenData = this.tokenService.get();
    // if (!tokenData.token) {
    //   this.injector.get(Router).navigateByUrl('/passport/login');
    //   resolve({});
    //   return;
    // }
    // mock
    const app: any = {
      name: `ng-alain`,
      description: `Ng-zorro admin panel front-end framework`
    };
    const user: any = {
      name: 'Admin',
      avatar: './assets/tmp/img/avatar.jpg',
      email: 'cipchk@qq.com',
      token: '123456789'
    };
    // 应用信息：包括站点名、描述、年份
    this.settingService.setApp(app);
    // 用户信息：包括姓名、头像、邮箱地址
    this.settingService.setUser(user);
    // ACL：设置权限为全量
    this.aclService.setFull(true);
    // 初始化菜单
    this.menuService.add([
      {
        text: '主导航',
        group: true,
        children: [
          {
            text: '仪表盘',
            link: '/dashboard',
            icon: 'anticon anticon-appstore-o'
          },
          {
            text: '快捷菜单',
            icon: 'anticon anticon-rocket',
            shortcut_root: true
          },
          {
            text: '系统管理',
            icon: 'anticon anticon-rocket',
            children: [{
              text: '用户管理',
              link: '/sys/user',
            }, {
              text: '模块管理',
              link: '/sys/module',
            }]
          },
        ]
      }
    ]);
    // 设置页面标题的后缀
    this.titleService.suffix = app.name;

    resolve({});
  }

  load(): Promise<any> {
    // only works with promises
    // https://github.com/angular/angular/issues/15088
    return new Promise((resolve, reject) => {
      // http
      this.viaHttp(resolve, reject);
      // mock：请勿在生产环境中这么使用，viaMock 单纯只是为了模拟一些数据使脚手架一开始能正常运行
      // this.viaMock(resolve, reject);
    });
  }
}
