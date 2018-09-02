import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BasicUserComponent} from "./user/user.component";

const routes: Routes = [
  {path: 'user', component: BasicUserComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SysRoutingModule {
}
