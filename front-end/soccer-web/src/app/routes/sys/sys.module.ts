import {NgModule} from '@angular/core';
import {SysRoutingModule} from './sys-routing.module';
import {SharedModule} from "../../shared/shared.module";
import {BasicUserComponent} from './user/user.component';
import {BasicUserEditComponent} from './user/edit/edit.component';
import {BasicUserViewComponent} from './user/view/view.component';
import { SysModuleComponent } from './module/module.component';
import { SysModuleEditComponent } from './module/edit/edit.component';
import { SysModuleViewComponent } from './module/view/view.component';
import { ProfileComponent } from './profile/profile.component';

const COMPONENTS = [
  BasicUserComponent,
  SysModuleComponent];
const COMPONENTS_NOROUNT = [
  BasicUserEditComponent,
  BasicUserViewComponent,
  SysModuleEditComponent,
  SysModuleViewComponent];

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
