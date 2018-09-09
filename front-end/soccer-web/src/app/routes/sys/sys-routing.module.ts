import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BasicUserComponent} from "./user/user.component";
import {SysModuleComponent} from './module/module.component';
import {ProfileComponent} from "./profile/profile.component";

const routes: Routes = [
  {path: 'user', component: BasicUserComponent},
  {path: 'module', component: SysModuleComponent},
  {path: 'profile', component: ProfileComponent, data: {title: '个人资料'}},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SysRoutingModule {
}
