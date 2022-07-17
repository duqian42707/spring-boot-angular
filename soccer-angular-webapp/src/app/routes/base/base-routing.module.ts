import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseUserComponent } from './user/user.component';
import { BaseRoleComponent } from './role/role.component';
import { BaseMenuComponent } from './menu/menu.component';

const routes: Routes = [

  { path: 'user', component: BaseUserComponent },
  { path: 'role', component: BaseRoleComponent },
  { path: 'menu', component: BaseMenuComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BaseRoutingModule { }
