import {Component, OnInit} from '@angular/core';
import {NzMessageService, NzModalRef} from 'ng-zorro-antd';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModuleService} from "@core/service/sys/module.service";

@Component({
  selector: 'app-sys-module-edit',
  templateUrl: './edit.component.html',
  // styleUrls: ['./edit.component.less']
})
export class SysModuleEditComponent implements OnInit {
  record: any = {};
  i: any;
  form: FormGroup;
  levelList=[{value: '0', name: '一级模块'}, {value: '1', name: '二级模块'}];
  submitting = false;


  constructor(
    private moduleService: ModuleService,
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [null, [Validators.required]],
      url: [null, [Validators.required]],
      type: [null, []],
      levelNo: [null, []],
      parentId: [null, []],
      orderNo: [null, []],
    });

    if (this.record.id > 0)
      this.moduleService.getOne(this.record.id).subscribe(res => {
        this.i = res.data;
        this.form.patchValue(res.data)
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

    this.submitting = true;
    this.moduleService.save(value).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    }, err => {
    }, () => {
      this.submitting = false;
    });
  }

  close() {
    this.modal.destroy(true);
  }
}
