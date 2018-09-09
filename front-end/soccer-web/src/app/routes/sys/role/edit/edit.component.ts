import {Component, OnInit, ViewChild} from '@angular/core';
import {NzModalRef, NzMessageService} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {SFSchema, SFUISchema} from '@delon/form';
import {FormBuilder, Validators} from "@angular/forms";
import {RoleService} from "@core/service/sys/role.service";

@Component({
  selector: 'app-sys-role-edit',
  templateUrl: './edit.component.html',
})
export class SysRoleEditComponent implements OnInit {
  record: any = {};
  i: any;
  form;
  submitting = false;

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    public roleService: RoleService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      roleName: [null, [Validators.required]],
    });
    if (this.record.id > 0)
      this.roleService.get(this.record.id).subscribe(res => {
        this.i = res.data;
        this.form.patchValue(res.data)
      });
  }

  save() {
    const value = this.form.getRawValue()
    if (this.record.id > 0) {
      value.id = this.record.id;
    }
    this.roleService.save(value).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close() {
    this.modal.destroy();
  }
}
