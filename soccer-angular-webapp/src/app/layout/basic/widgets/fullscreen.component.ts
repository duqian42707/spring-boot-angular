import { ChangeDetectionStrategy, Component, HostListener } from '@angular/core';
import screenfull from 'screenfull';

@Component({
  selector: 'header-fullscreen',
  template: `
    <i nz-icon [nzType]="status ? 'fullscreen-exit' : 'fullscreen'"></i>
    {{ status ? '退出全屏' : '全屏' }}
  `,
  host: {
    '[class.flex-1]': 'true'
  },
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeaderFullScreenComponent {
  status = false;

  @HostListener('window:resize')
  _resize(): void {
    this.status = screenfull.isFullscreen;
  }

  @HostListener('click')
  _click(): void {
    if (screenfull.isEnabled) {
      screenfull.toggle();
    }
  }
}
