import {NgModule, Type} from '@angular/core';
import {SharedModule} from '@shared';
import {SysRoutingModule} from './sys-routing.module';
import {SysLogComponent} from './log/log.component';
import {SysUserComponent} from './user/user.component';
import {SysRoleComponent} from './role/role.component';
import {SysMenuComponent} from './menu/menu.component';
import {SysRoleEditComponent} from './role/edit/edit.component';
import {SysMenuEditComponent} from './menu/edit/edit.component';
import {SysUserEditComponent} from './user/edit/edit.component';
import {SysMenuAuthComponent} from './menu/auth/auth.component';
import {SysRoleMenuComponent} from "./role/menu/menu.component";
import {SysMenuService} from "./menu/sys-menu.service";

const COMPONENTS: Type<void>[] = [
  SysLogComponent, SysUserComponent, SysRoleComponent, SysMenuComponent,
  SysRoleEditComponent, SysMenuEditComponent, SysUserEditComponent,
  SysMenuAuthComponent, SysRoleMenuComponent];

@NgModule({
  imports: [
    SharedModule,
    SysRoutingModule,
  ],
  declarations: COMPONENTS,
  providers: [
    SysMenuService
  ],
})
export class SysModule {
}
