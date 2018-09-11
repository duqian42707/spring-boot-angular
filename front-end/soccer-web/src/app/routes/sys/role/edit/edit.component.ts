import {Component, OnInit, ViewChild} from '@angular/core';
import {NzModalRef, NzMessageService, NzTreeComponent} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {SFSchema, SFUISchema} from '@delon/form';
import {FormBuilder, Validators} from "@angular/forms";
import {RoleService} from "@core/service/sys/role.service";
import {ModuleService} from "@core/service/sys/module.service";

@Component({
  selector: 'app-sys-role-edit',
  templateUrl: './edit.component.html',
})
export class SysRoleEditComponent implements OnInit {
  @ViewChild('nzTree') nzTree: NzTreeComponent;
  record: any = {};
  i: any;
  form;
  submitting = false;

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    public roleService: RoleService,
    public moduleService: ModuleService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      roleName: [null, [Validators.required]],
      modules: [null, [Validators.required]],
    });
    this.moduleService.getMenuForTreeNode().subscribe(res => {
      this.form.patchValue({modules: res})
    })

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
    let modules = this.getCheckedLeafNodes(value.modules);
    value.moduleList = modules.map(v=>(
      {
        module:{id:v.key}
      }
    ));
    delete value.modules;

    this.roleService.save(value).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }


  private getCheckedLeafNodes(nodes) {
    let arr = [];
    nodes.forEach(v => {
      if (v.isLeaf && v.isChecked) {
        arr.push(v)
      } else {
        arr = [...arr, ...this.getCheckedLeafNodes(v.children)]
      }
    });
    return arr;
  }

  close() {
    this.modal.destroy();
  }
}
