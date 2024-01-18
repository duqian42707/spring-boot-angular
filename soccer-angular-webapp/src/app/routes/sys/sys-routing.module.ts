import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SysLogComponent} from './log/log.component';
import {SysUserComponent} from './user/user.component';
import {SysRoleComponent} from './role/role.component';
import {SysMenuComponent} from './menu/menu.component';
import {SysAuthComponent} from "./auth/auth.component";
import {SysConfigComponent} from "./config/config.component";
import {SysDeptComponent} from "./dept/dept.component";

const routes: Routes = [
  {path: 'user', component: SysUserComponent},
  {path: 'role', component: SysRoleComponent},
  {path: 'menu', component: SysMenuComponent},
  {path: 'auth', component: SysAuthComponent},
  {path: 'dept', component: SysDeptComponent},
  {path: 'config', component: SysConfigComponent},
  {path: 'log', component: SysLogComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SysRoutingModule {
}
