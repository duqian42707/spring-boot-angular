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
import { SysMenuEditComponent } from './menu/edit/edit.component';
import { SysUserEditComponent } from './user/edit/edit.component';
import { SysMenuAuthComponent } from './menu/auth/auth.component';

const COMPONENTS: Type<void>[] = [
  SysLogComponent, SysUserComponent, SysRoleComponent, SysMenuComponent,
  SysRoleEditComponent,
  SysMenuEditComponent,
  SysUserEditComponent,
  SysMenuAuthComponent];

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
