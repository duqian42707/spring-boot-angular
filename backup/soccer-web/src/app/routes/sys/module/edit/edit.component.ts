import {Component, OnInit} from '@angular/core';
import {NzMessageService, NzModalRef, NzTreeNode} from 'ng-zorro-antd';
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
  parentList: NzTreeNode[];
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
      url: [null, []],
      type: [null, []],
      levelNo: [null, []],
      parentId: [null, []],
      orderNo: [null, []],
      icon: [null, []],
    });
    this.moduleService.getMenuForTreeNode().subscribe(res => {
      this.parentList = res;
    })
    if (this.record.id > 0) {
      this.moduleService.getOne(this.record.id).subscribe(res => {
        this.i = res;
        this.form.patchValue(res)
      });
    } else {
      this.i = {}
      if (this.record.parentId > 0) {
        this.i = {parentId: this.record.parentId}
        this.form.patchValue(this.i)
      }
    }
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
