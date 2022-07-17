import {Component, OnInit} from '@angular/core';
import {NzMessageService, NzModalRef} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RoleService} from "@core/service/sys/role.service";
import {UserService} from "@core/service/sys/user.service";

@Component({
  selector: 'app-basic-user-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.less']
})
export class BasicUserEditComponent implements OnInit {
  record: any = {};
  i: any;
  form: FormGroup;
  genderList = [{value: '1', name: '男'}, {value: '2', name: '女'}];
  roleList;
  submitting = false;

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    private userService: UserService,
    private roleService: RoleService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      account: [null, [Validators.required]],
      userName: [null, [Validators.required]],
      roles: [null, [Validators.required]],
      phone: [null, []],
      gender: [null, []],
    });

    this.roleService.getRoleList().subscribe(res => {
      this.roleList = res.data;
    })

    if (this.record.id > 0)
      this.userService.get(this.record.id).subscribe(res => {
        this.i = res.data;
        this.i.roles = this.i.roles.map(v => v.id);
        this.form.patchValue(this.i)
      });
  }

  save() {
    for (const i in this.form.controls) {
      this.form.controls[i].markAsDirty();
      this.form.controls[i].updateValueAndValidity();
    }
    if (this.form.invalid) return;

    for (let p in this.i) {
      if (this.form.contains(p)) {
        this.i[p] = this.form.controls[p].value
      }
    }
    let value;
    if (this.i.id > 0) {
      value = this.i
    } else {
      value = this.form.getRawValue()
    }

    value.roles = value.roles.map(v => ({id: v}))

    this.submitting = true;
    this.userService.save(value).subscribe(res => {
      this.submitting = false;
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close() {
    this.modal.destroy(true);
  }
}
