import {Injectable, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {DA_SERVICE_TOKEN, ITokenService} from '@delon/auth';
import {ALAIN_I18N_TOKEN, MenuService, SettingsService, TitleService} from '@delon/theme';
import {ACLService} from '@delon/acl';
import {Observable, zip, of, catchError, map} from 'rxjs';
import type {NzSafeAny} from 'ng-zorro-antd/core/types';
import {NzIconService} from 'ng-zorro-antd/icon';

import {ICONS} from '../../../style-icons';
import {ICONS_AUTO} from '../../../style-icons-auto';

/**
 * Used for application startup
 * Generally used to get the basic data of the application, like: Menu Data, User Data, etc.
 */
@Injectable()
export class StartupService {
  constructor(
    iconSrv: NzIconService,
    private menuService: MenuService,
    private settingService: SettingsService,
    private aclService: ACLService,
    private titleService: TitleService,
    @Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
    private httpClient: HttpClient,
    private router: Router
  ) {
    iconSrv.addIcon(...ICONS_AUTO, ...ICONS);
  }


  load(): Observable<void> {
    return this.httpClient.get('assets/tmp/app-data.json').pipe(
      catchError((res: NzSafeAny) => {
        console.warn(`StartupService.load: Network request failed`, res);
        setTimeout(() => this.router.navigateByUrl(`/exception/500`));
        return of({});
      }),
      map((res: NzSafeAny) => {
        // Application information: including site name, description, year
        this.settingService.setApp(res.app);
        // User information: including name, avatar, email address
        this.settingService.setUser(res.user);
        // ACL: Set the permissions to full, https://ng-alain.com/acl/getting-started
        this.aclService.setFull(true);
        // Menu data, https://ng-alain.com/theme/menu
        this.menuService.add(res.menu);
        // Can be set page suffix title, https://ng-alain.com/theme/title
        this.titleService.suffix = res.app.name;
      })
    );
  }
}