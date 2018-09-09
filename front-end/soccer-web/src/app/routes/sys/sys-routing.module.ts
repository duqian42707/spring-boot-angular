import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BasicUserComponent} from "./user/user.component";
import {SysModuleComponent} from './module/module.component';
import {ProfileComponent} from "./profile/profile.component";
import {SysRoleComponent} from './role/role.component';

const routes: Routes = [

  {path: 'profile', component: ProfileComponent, data: {title: '个人资料'}},
  //用户管理
  {path: 'user', component: BasicUserComponent},
  //模块管理
  {path: 'module', component: SysModuleComponent},
  //角色管理
  {path: 'role', component: SysRoleComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SysRoutingModule {
}
