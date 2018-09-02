import {NgModule} from '@angular/core';

import {SharedModule} from "@shared/shared.module";
import {LoginService} from "@core/service/sys/login.service";
import {UserService} from "@core/service/sys/user.service";

@NgModule({
  imports: [
    SharedModule
  ],
  providers: [
    LoginService,
    UserService
  ]
})
export class ServiceModule {
}
