import {NgModule} from '@angular/core';

import {SharedModule} from "@shared/shared.module";
import {LoginService} from "@core/service/sys/login.service";

@NgModule({
  imports: [
    SharedModule
  ],
  providers: [
    LoginService
  ]
})
export class ServiceModule {
}
