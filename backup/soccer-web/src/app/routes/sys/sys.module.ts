import {NgModule} from '@angular/core';
import {SysRoutingModule} from './sys-routing.module';
import {SharedModule} from "../../shared/shared.module";
import {BasicUserComponent} from './user/user.component';
import {BasicUserEditComponent} from './user/edit/edit.component';
import {BasicUserViewComponent} from './user/view/view.component';
import {SysModuleComponent} from './module/module.component';
import {SysModuleEditComponent} from './module/edit/edit.component';
import {SysModuleViewComponent} from './module/view/view.component';
import {ProfileComponent} from './profile/profile.component';
import {SysRoleComponent} from './role/role.component';
import {SysRoleEditComponent} from './role/edit/edit.component';
import {SysRoleViewComponent} from './role/view/view.component';
import { SysDictComponent } from './dict/dict.component';
import { SysDictEditComponent } from './dict/edit/edit.component';
import { SysDictViewComponent } from './dict/view/view.component';

const COMPONENTS = [
  BasicUserComponent,
  SysModuleComponent,
  SysRoleComponent,
  SysDictComponent];
const COMPONENTS_NOROUNT = [
  BasicUserEditComponent,
  BasicUserViewComponent,
  SysModuleEditComponent,
  SysModuleViewComponent,
  SysRoleEditComponent,
  SysRoleViewComponent,
  SysDictEditComponent,
  SysDictViewComponent];

@NgModule({
  imports: [
    SharedModule,
    SysRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT,
    ProfileComponent
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class SysModule {
}
