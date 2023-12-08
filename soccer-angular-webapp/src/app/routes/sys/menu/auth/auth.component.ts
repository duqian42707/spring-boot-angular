import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {_HttpClient} from '@delon/theme';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalRef} from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-sys-menu-auth',
  templateUrl: './auth.component.html',
})
export class SysMenuAuthComponent implements OnInit {
  i: any;
  menuId: any;
  powerList: any[] = [];
  editCache: { [key: string]: any } = {};
  listLoading = false;


  constructor(
    private modal: NzModalRef,
    private msgSrv: NzMessageService,
    public http: _HttpClient,
    private fb: FormBuilder,
    private ms: NzMessageService
  ) {
  }

  ngOnInit(): void {
    this.menuId = this.i.menuId;
    this.loadData();
  }

  loadData() {
    this.http.get(`/api/auth/all?menuId=${this.menuId}`).subscribe((res: any) => {
      this.powerList = res.data;
      this.updateEditCache();
    });
  }

  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: string): void {
    const index = this.powerList.findIndex(item => item.authId === id);
    this.powerList.splice(index, 1);
    this.powerList = [...this.powerList];
  }

  saveEdit(authId: string): void {
    // 判断数据中是否有字段为空
    if (!this.checkNullData(authId)) {
      return;
    }
    this.listLoading = true;
    const index = this.powerList.findIndex(item => item.authId === authId);
    Object.assign(this.powerList[index], this.editCache[authId].data);
    this.editCache[authId].edit = false;
    const element = this.editCache[authId].data;
    const url = element.authId == null ? '/api/auth/insert' : '/api/auth/update';
    this.http.post(url, {
      authId: element.authId,
      authName: element.authName,
      authValue: element.authValue,
      displayIndex: element.displayIndex,
      menuId: this.menuId,
    }).subscribe((res: any) => {
      this.msgSrv.success(res.msg);
      this.loadData();
      this.listLoading = false;
    });
  }

  // 数据是否未填写完全
  checkNullData(id: string) {
    if (this.editCache[id].data.authName === null || this.editCache[id].data.authName === '') {
      this.ms.warning('请填写权限名称！');
      return false;
    }
    if (this.editCache[id].data.authValue === null || this.editCache[id].data.authValue === '') {
      this.ms.warning('请填写权限代号！');
      return false;
    }
    return true;
  }

  updateEditCache(): void {
    this.powerList.forEach(item => {
      this.editCache[item.authId] = {
        edit: false,
        data: {...item}
      };
    });
  }

  recordDel(id: string) {
    this.http.post(`/api/auth/delete/${id}`, {}).subscribe((res: any) => {
      this.ms.success(res.msg);
      this.loadData();
    });
  }

  // 添加表格数据条目
  addListData() {
    this.powerList = [
      ...this.powerList,
      {
        id: '__random__' + Math.random(),
        name: '',
        value: '',
        isNew: true
      }
    ];

    this.powerList.forEach((item, index) => {
      this.editCache[item.authId] = {
        edit: item.isNew,
        data: {...item}
      };
    });
  }

  close(): void {
    this.modal.destroy();
  }
}
