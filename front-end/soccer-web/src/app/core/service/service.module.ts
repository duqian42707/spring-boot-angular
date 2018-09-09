import {NgModule} from '@angular/core';

import {SharedModule} from "@shared/shared.module";
import {LoginService} from "@core/service/sys/login.service";
import {UserService} from "@core/service/sys/user.service";
import {ModuleService} from "@core/service/sys/module.service";
import {RoleService} from "@core/service/sys/role.service";

@NgModule({
  imports: [
    SharedModule
  ],
  providers: [
    LoginService,
    UserService,
    ModuleService,
    RoleService
  ]
})
export class ServiceModule {
}
