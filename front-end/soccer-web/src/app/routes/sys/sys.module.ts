import {NgModule} from '@angular/core';
import {SysRoutingModule} from './sys-routing.module';
import {SharedModule} from "../../shared/shared.module";
import {BasicUserComponent} from './user/user.component';
import {BasicUserEditComponent} from './user/edit/edit.component';
import {BasicUserViewComponent} from './user/view/view.component';

const COMPONENTS = [
  BasicUserComponent];
const COMPONENTS_NOROUNT = [
  BasicUserEditComponent,
  BasicUserViewComponent];

@NgModule({
  imports: [
    SharedModule,
    SysRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class SysModule {
}
