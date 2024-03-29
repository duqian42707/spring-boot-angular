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
import {SysRoleMenuComponent} from "./role/menu/menu.component";
import {SysMenuService} from "./menu/sys-menu.service";
import {SysAuthComponent} from "./auth/auth.component";
import {SysAuthEditComponent} from "./auth/edit/edit.component";
import {SysRoleAuthComponent} from "./role/auth/auth.component";
import {SysAuthService} from "./auth/sys-auth.service";
import {SysConfigComponent} from "./config/config.component";
import {SysDeptComponent} from "./dept/dept.component";
import {SysProfileComponent} from "./profile/profile.component";
import {ProfileBasicComponent} from "./profile/profile-basic/profile-basic.component";
import {ProfileSafetyComponent} from "./profile/profile-safety/profile-safety.component";
import {SysDeptEditComponent} from "./dept/edit/edit.component";
import {SysDeptService} from "./dept/sys-dept.service";

const COMPONENTS: Type<void>[] = [
  SysUserComponent, SysUserEditComponent,
  SysRoleComponent, SysRoleEditComponent, SysRoleMenuComponent, SysRoleAuthComponent,
  SysMenuComponent, SysMenuEditComponent,
  SysAuthComponent, SysAuthEditComponent,
  SysDeptComponent, SysDeptEditComponent,
  SysConfigComponent, SysLogComponent,
  SysProfileComponent, ProfileBasicComponent, ProfileSafetyComponent
];

@NgModule({
  imports: [
    SharedModule,
    SysRoutingModule,
  ],
  declarations: COMPONENTS,
  providers: [
    SysMenuService,
    SysAuthService,
    SysDeptService
  ],
})
export class SysModule {
}
