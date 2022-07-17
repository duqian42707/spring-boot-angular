import { NgModule, Type } from '@angular/core';
import { SharedModule } from '@shared';
import { SysRoutingModule } from './sys-routing.module';
import { SysService } from './sys.service';
import { SysLogComponent } from './log/log.component';
import { SysLogService } from './log/log.service';

const COMPONENTS: Type<void>[] = [
  SysLogComponent];

@NgModule({
  imports: [
    SharedModule,
    SysRoutingModule
  ],
  declarations: COMPONENTS,
  providers: [
    SysService,
    SysLogService
  ],
})
export class SysModule { }
