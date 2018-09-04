import {Component, OnInit} from '@angular/core';
import {NzMessageService, NzModalRef} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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
  submitting = false;

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    public http: _HttpClient,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      account: [null, [Validators.required]],
      userName: [null, [Validators.required]],
      phone: [null, []],
      gender: [null, []],
    });
    if (this.record.id > 0)
      this.http.get<any>(`basic/user/get/${this.record.id}`).subscribe(res => {
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
    this.http.post(`basic/user/save`, value).subscribe(res => {
      this.submitting = false;
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close() {
    this.modal.destroy(true);
  }
}
