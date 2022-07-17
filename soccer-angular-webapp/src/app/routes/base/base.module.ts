import { NgModule, Type } from '@angular/core';
import { SharedModule } from '@shared';
import { BaseRoutingModule } from './base-routing.module';
import { BaseService } from './base.service';
import { BaseUserComponent } from './user/user.component';
import { BaseUserService } from './user/user.service';
import { BaseRoleComponent } from './role/role.component';
import { BaseRoleService } from './role/role.service';
import { BaseMenuComponent } from './menu/menu.component';
import { BaseMenuService } from './menu/menu.service';

const COMPONENTS: Type<void>[] = [
  BaseUserComponent,
  BaseRoleComponent,
  BaseMenuComponent];

@NgModule({
  imports: [
    SharedModule,
    BaseRoutingModule
  ],
  declarations: COMPONENTS,
  providers: [
    BaseService,
    BaseUserService,
    BaseRoleService,
    BaseMenuService
  ],
})
export class BaseModule { }
