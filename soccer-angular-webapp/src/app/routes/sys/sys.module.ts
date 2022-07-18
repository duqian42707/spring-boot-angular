import {NgModule, Type} from '@angular/core';
import {SharedModule} from '@shared';
import {SysRoutingModule} from './sys-routing.module';
import {SysService} from './sys.service';
import {SysLogComponent} from './log/log.component';
import {SysLogService} from './log/log.service';
import {SysUserComponent} from './user/user.component';
import {SysRoleComponent} from './role/role.component';
import {SysMenuComponent} from './menu/menu.component';
import {SysUserService} from './user/user.service';
import {SysRoleService} from './role/role.service';
import {SysMenuService} from './menu/menu.service';
import { SysRoleEditComponent } from './role/edit/edit.component';

const COMPONENTS: Type<void>[] = [
  SysLogComponent, SysUserComponent, SysRoleComponent, SysMenuComponent,
  SysRoleEditComponent];

@NgModule({
  imports: [
    SharedModule,
    SysRoutingModule
  ],
  declarations: COMPONENTS,
  providers: [
    SysService,
    SysLogService,
    SysUserService,
    SysRoleService,
    SysMenuService
  ],
})
export class SysModule {
}
