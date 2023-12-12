"use strict";(self.webpackChunkportal=self.webpackChunkportal||[]).push([[44],{5666:(Qt,N,l)=>{l.r(N),l.d(N,{SysModule:()=>Lt});var q=l(3364),w=l(2787),t=l(5879),d=l(7776),y=l(650),C=l(9384),v=l(1168),T=l(2962);const Y=["st"],O=function(){return{toTop:!1}},R=function(){return{pi:"pageNum",ps:"pageSize"}},E=function(n){return{reName:n}},$=function(){return{total:"data.total",list:"data.list"}};let F=(()=>{var n;class a{constructor(e,i){this.http=e,this.modal=i,this.url="/api/log/list",this.searchSchema={properties:{no:{type:"string",title:"\u7f16\u53f7"}}},this.columns=[{title:"\u7f16\u53f7",type:"no"},{title:"\u8d26\u53f7",index:"username"},{title:"\u6635\u79f0",index:"nickName"},{title:"\u8bf7\u6c42\u5730\u5740",index:"requestUrl"},{title:"\u8017\u65f6",type:"number",index:"runTime"},{title:"\u65f6\u95f4",type:"date",index:"createdDate"}]}ngOnInit(){}add(){}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(d.lP),t.Y36(d.Te))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-log"]],viewQuery:function(e,i){if(1&e&&t.Gf(Y,5),2&e){let s;t.iGM(s=t.CRH())&&(i.st=s.first)}},decls:5,vars:13,consts:[["mode","search",3,"schema","formSubmit","formReset"],[3,"data","page","req","res","columns"],["st",""]],template:function(e,i){if(1&e){const s=t.EpF();t._UZ(0,"page-header"),t.TgZ(1,"nz-card")(2,"sf",0),t.NdJ("formSubmit",function(c){t.CHM(s);const u=t.MAs(4);return t.KtG(u.reset(c))})("formReset",function(c){t.CHM(s);const u=t.MAs(4);return t.KtG(u.reset(c))}),t.qZA(),t._UZ(3,"st",1,2),t.qZA()}2&e&&(t.xp6(2),t.Q6J("schema",i.searchSchema),t.xp6(1),t.Q6J("data",i.url)("page",t.DdM(6,O))("req",t.VKq(8,E,t.DdM(7,R)))("res",t.VKq(11,E,t.DdM(10,$)))("columns",i.columns))},dependencies:[y.kJ,C.q,v.A5,T.bd],encapsulation:2}),a})();var M=l(1221),x=l(6691),m=l(6814),h=l(2840),f=l(855),g=l(1958),z=l(2669);function K(n,a){1&n&&t._UZ(0,"nz-spin",4)}function G(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"sf",5,6)(2,"div",7)(3,"button",8),t.NdJ("click",function(){t.CHM(o);const i=t.oxw();return t.KtG(i.close())}),t._uU(4,"\u5173\u95ed"),t.qZA(),t.TgZ(5,"button",9),t.NdJ("click",function(){t.CHM(o);const i=t.MAs(1),s=t.oxw();return t.KtG(s.save(i.value))}),t._uU(6,"\u4fdd\u5b58"),t.qZA()()()}if(2&n){const o=t.MAs(1),e=t.oxw();t.Q6J("schema",e.schema)("ui",e.ui)("formData",e.i),t.xp6(5),t.Q6J("disabled",!o.valid)("nzLoading",e.http.loading)}}let A=(()=>{var n;class a{constructor(e,i,s){this.modal=e,this.msgSrv=i,this.http=s,this.record={},this.schema={properties:{no:{type:"string",title:"\u7f16\u53f7"},owner:{type:"string",title:"\u59d3\u540d",maxLength:15},callNo:{type:"number",title:"\u8c03\u7528\u6b21\u6570"},href:{type:"string",title:"\u94fe\u63a5",format:"uri"},description:{type:"string",title:"\u63cf\u8ff0",maxLength:140}},required:["owner","callNo","href","description"]},this.ui={"*":{spanLabelFixed:100,grid:{span:12}},$no:{widget:"text"},$href:{widget:"string"},$description:{widget:"textarea",grid:{span:24}}}}ngOnInit(){this.record.id>0&&this.http.get(`/user/${this.record.id}`).subscribe(e=>this.i=e)}save(e){this.http.post(`/user/${this.record.id}`,e).subscribe(i=>{this.msgSrv.success("\u4fdd\u5b58\u6210\u529f"),this.modal.close(!0)})}close(){this.modal.destroy()}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(M.Lf),t.Y36(x.dD),t.Y36(d.lP))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-user-edit"]],decls:5,vars:3,consts:[[1,"modal-header"],[1,"modal-title"],["class","modal-spin",4,"ngIf"],["mode","edit","button","none",3,"schema","ui","formData",4,"ngIf"],[1,"modal-spin"],["mode","edit","button","none",3,"schema","ui","formData"],["sf",""],[1,"modal-footer"],["nz-button","","type","button",3,"click"],["nz-button","","type","submit","nzType","primary",3,"disabled","nzLoading","click"]],template:function(e,i){1&e&&(t.TgZ(0,"div",0)(1,"div",1),t._uU(2),t.qZA()(),t.YNc(3,K,1,0,"nz-spin",2),t.YNc(4,G,7,5,"sf",3)),2&e&&(t.xp6(2),t.hij("\u7f16\u8f91 ",i.record.id," \u4fe1\u606f"),t.xp6(1),t.Q6J("ngIf",!i.i),t.xp6(1),t.Q6J("ngIf",i.i))},dependencies:[m.O5,y.kJ,h.ix,f.w,g.dQ,z.W],encapsulation:2}),a})();const H=["st"];function B(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"button",5),t.NdJ("click",function(){t.CHM(o);const i=t.oxw();return t.KtG(i.add())}),t._uU(1,"\u65b0\u5efa"),t.qZA()}}const V=function(){return{total:"data.total",list:"data.list"}},P=function(n){return{reName:n}};let J=(()=>{var n;class a{constructor(e,i){this.http=e,this.modal=i,this.url="/api/user/list",this.searchSchema={properties:{no:{type:"string",title:"\u7f16\u53f7"}}},this.columns=[{title:"\u7f16\u53f7",type:"no"},{title:"\u8d26\u53f7",index:"account"},{title:"\u6635\u79f0",index:"nickName"},{title:"\u5934\u50cf",type:"img",width:"64px",index:"avatarUrl"},{title:"\u65f6\u95f4",type:"date",index:"updatedAt"},{title:"\u64cd\u4f5c",buttons:[{text:"\u7f16\u8f91",type:"modal",modal:{component:A},click:"reload"}]}]}ngOnInit(){}add(){this.modal.createStatic(A,{i:{id:0}}).subscribe(()=>this.st.reload())}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(d.lP),t.Y36(d.Te))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-user"]],viewQuery:function(e,i){if(1&e&&t.Gf(H,5),2&e){let s;t.iGM(s=t.CRH())&&(i.st=s.first)}},decls:7,vars:8,consts:[[3,"action"],["phActionTpl",""],["mode","search",3,"schema","formSubmit","formReset"],[3,"data","res","columns"],["st",""],["nz-button","","nzType","primary",3,"click"]],template:function(e,i){if(1&e){const s=t.EpF();t.TgZ(0,"page-header",0),t.YNc(1,B,2,0,"ng-template",null,1,t.W1O),t.qZA(),t.TgZ(3,"nz-card")(4,"sf",2),t.NdJ("formSubmit",function(c){t.CHM(s);const u=t.MAs(6);return t.KtG(u.reset(c))})("formReset",function(c){t.CHM(s);const u=t.MAs(6);return t.KtG(u.reset(c))}),t.qZA(),t._UZ(5,"st",3,4),t.qZA()}if(2&e){const s=t.MAs(2);t.Q6J("action",s),t.xp6(4),t.Q6J("schema",i.searchSchema),t.xp6(1),t.Q6J("data",i.url)("res",t.VKq(6,P,t.DdM(5,V)))("columns",i.columns)}},dependencies:[y.kJ,C.q,v.A5,h.ix,f.w,g.dQ,T.bd],encapsulation:2}),a})();function W(n,a){1&n&&t._UZ(0,"nz-spin",4)}function j(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"sf",5,6)(2,"div",7)(3,"button",8),t.NdJ("click",function(){t.CHM(o);const i=t.oxw();return t.KtG(i.close())}),t._uU(4,"\u5173\u95ed"),t.qZA(),t.TgZ(5,"button",9),t.NdJ("click",function(){t.CHM(o);const i=t.MAs(1),s=t.oxw();return t.KtG(s.save(i.value))}),t._uU(6,"\u4fdd\u5b58"),t.qZA()()()}if(2&n){const o=t.MAs(1),e=t.oxw();t.Q6J("schema",e.schema)("ui",e.ui)("formData",e.i),t.xp6(5),t.Q6J("disabled",!o.valid)("nzLoading",e.http.loading)}}let Z=(()=>{var n;class a{constructor(e,i,s){this.modal=e,this.msgSrv=i,this.http=s,this.record={},this.schema={properties:{roleValue:{type:"string",title:"\u89d2\u8272\u6807\u8bc6"},roleName:{type:"string",title:"\u89d2\u8272\u540d\u79f0"}},required:["roleName","roleValue"]},this.ui={"*":{spanLabelFixed:100,grid:{span:24}},$roleValue:{widget:"string"},$roleName:{widget:"string"}}}ngOnInit(){console.log("init edit",this.record),null!=this.record.roleId&&this.http.get(`/api/role/info/${this.record.roleId}`).subscribe(e=>this.i=e.data)}save(e){this.http.post(null!=this.record.roleId?"/api/role/update":"/api/role/insert",e).subscribe(s=>{this.msgSrv.success(s.msg),this.modal.close(!0)})}close(){this.modal.destroy()}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(M.Lf),t.Y36(x.dD),t.Y36(d.lP))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-role-edit"]],decls:5,vars:3,consts:[[1,"modal-header"],[1,"modal-title"],["class","modal-spin",4,"ngIf"],["mode","edit","button","none",3,"schema","ui","formData",4,"ngIf"],[1,"modal-spin"],["mode","edit","button","none",3,"schema","ui","formData"],["sf",""],[1,"modal-footer"],["nz-button","","type","button",3,"click"],["nz-button","","type","submit","nzType","primary",3,"disabled","nzLoading","click"]],template:function(e,i){1&e&&(t.TgZ(0,"div",0)(1,"div",1),t._uU(2),t.qZA()(),t.YNc(3,W,1,0,"nz-spin",2),t.YNc(4,j,7,5,"sf",3)),2&e&&(t.xp6(2),t.hij("\u7f16\u8f91 ",i.record.id," \u4fe1\u606f"),t.xp6(1),t.Q6J("ngIf",!i.i),t.xp6(1),t.Q6J("ngIf",i.i))},dependencies:[m.O5,y.kJ,h.ix,f.w,g.dQ,z.W],encapsulation:2}),a})();var tt=l(3389),et=l(7398);let k=(()=>{var n;class a{constructor(e){this.http=e}loadMenuAuthTree(){return this.http.get("/api/menu/tree").pipe((0,et.U)(e=>this.buildTreeNodes(e.data,"menu")))}buildTreeNodes(e,i){return null==e?[]:e.map("menu"==i?s=>{const u=[...this.buildTreeNodes(s.children,"menu"),...this.buildTreeNodes(s.auths,"auth")];return{...s,type:i,key:s.menuId,title:s.menuName,children:u,isLeaf:0==u.length}}:s=>({...s,type:i,key:s.authId,title:s.authName,children:[],isLeaf:!0}))}}return(n=a).\u0275fac=function(e){return new(e||n)(t.LFG(d.lP))},n.\u0275prov=t.Yz7({token:n,factory:n.\u0275fac}),a})();var nt=l(8600);const it=["treeComponent"];function ot(n,a){if(1&n&&t._UZ(0,"nz-tree",5,6),2&n){const o=t.oxw();t.Q6J("nzData",o.treeNodes)("nzCheckedKeys",o.defaultCheckedKeys)}}let U=(()=>{var n;class a{constructor(e,i,s,r){this.http=e,this.nzDrawerRef=i,this.nzMessageService=s,this.sysMenuService=r,this.record={},this.treeNodes=[],this.defaultCheckedKeys=[],this.loading=!1,this.saveLoading=!1}ngOnInit(){console.log(this.record),this.loadMenuAuthTree(),this.loadRoleInfo()}loadRoleInfo(){this.loading=!0,this.http.get("/api/role/info/"+this.record.roleId).subscribe(e=>{this.loading=!1;const i=e.data.menus.map(r=>r.menuId),s=e.data.auths.map(r=>r.authId);this.defaultCheckedKeys=[...i,...s]})}loadMenuAuthTree(){this.sysMenuService.loadMenuAuthTree().subscribe(e=>{this.treeNodes=e})}cancel(){this.nzDrawerRef.close()}ok(){const s=[...this.treeComponent.getCheckedNodeList(),...this.treeComponent.getHalfCheckedNodeList()],r=[],c=[];s.forEach(_=>{"menu"==_.origin.type?r.push(_.key):"auth"==_.origin.type&&c.push(_.key)});const u={roleId:this.record.roleId,menus:r.map(_=>({menuId:_})),auths:c.map(_=>({authId:_}))};this.saveLoading=!0,this.http.post("/api/role/saveRoleMenuAuth",u).subscribe(_=>{this.saveLoading=!1,this.nzMessageService.success(_.msg),this.nzDrawerRef.close()})}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(d.lP),t.Y36(tt.lB),t.Y36(x.dD),t.Y36(k))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-role-menu"]],viewQuery:function(e,i){if(1&e&&t.Gf(it,5),2&e){let s;t.iGM(s=t.CRH())&&(i.treeComponent=s.first)}},decls:7,vars:4,consts:[[2,"width","300px"],["nzCheckable","","nzMultiple","","nzExpandAll","",3,"nzData","nzCheckedKeys",4,"ngIf"],[1,"drawer-footer"],["nz-button","",3,"nzType","click"],["nz-button","",3,"nzType","nzLoading","click"],["nzCheckable","","nzMultiple","","nzExpandAll","",3,"nzData","nzCheckedKeys"],["treeComponent",""]],template:function(e,i){1&e&&(t.TgZ(0,"div",0),t.YNc(1,ot,2,2,"nz-tree",1),t.qZA(),t.TgZ(2,"div",2)(3,"button",3),t.NdJ("click",function(){return i.cancel()}),t._uU(4,"\u5173\u95ed"),t.qZA(),t.TgZ(5,"button",4),t.NdJ("click",function(){return i.ok()}),t._uU(6,"\u4fdd\u5b58"),t.qZA()()),2&e&&(t.xp6(1),t.Q6J("ngIf",i.treeNodes.length>0),t.xp6(2),t.Q6J("nzType","default"),t.xp6(2),t.Q6J("nzType","primary")("nzLoading",i.saveLoading))},dependencies:[m.O5,h.ix,f.w,g.dQ,nt.Hc],encapsulation:2}),a})();const st=["st"];function at(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"button",5),t.NdJ("click",function(){t.CHM(o);const i=t.oxw();return t.KtG(i.add())}),t._uU(1,"\u65b0\u5efa"),t.qZA()}}const rt=function(){return{total:"data.total",list:"data.list"}},ct=function(n){return{reName:n}};let D=(()=>{var n;class a{constructor(e,i,s){this.http=e,this.modal=i,this.drawerHelper=s,this.loading=null,this.url="/api/role/list",this.searchSchema={properties:{roleName:{type:"string",title:"\u89d2\u8272\u540d\u79f0"},roleValue:{type:"string",title:"\u89d2\u8272\u6807\u8bc6"}}},this.columns=[{title:"\u7f16\u53f7",type:"no"},{title:"\u89d2\u8272\u6807\u8bc6",index:"roleValue"},{title:"\u89d2\u8272\u540d\u79f0",index:"roleName"},{title:"\u66f4\u65b0\u4eba",index:"lastModifiedBy",format:r=>function X(n){return null!=n&&n.includes("~~~")?n.split("~~~")[1]:n}(r.lastModifiedBy)},{title:"\u66f4\u65b0\u65f6\u95f4",type:"date",index:"lastModifiedDate"},{title:"\u64cd\u4f5c",buttons:[{text:"\u7f16\u8f91",type:"modal",modal:{component:Z},click:"reload"},{text:"\u5220\u9664",type:"del",click:r=>this.delete(r)},{text:"\u914d\u7f6e\u6743\u9650",type:"drawer",drawer:{component:U,drawerOptions:{nzClosable:!1}},click:"reload"}]}]}ngOnInit(){}add(){this.modal.createStatic(Z,{i:{roleId:null}}).subscribe(()=>this.st.reload())}delete(e){this.loading=!0,this.http.post(`/api/role/delete/${e.roleId}`).subscribe(i=>{this.loading=null,this.st.reload()})}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(d.lP),t.Y36(d.Te),t.Y36(d.hC))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-role"]],viewQuery:function(e,i){if(1&e&&t.Gf(st,5),2&e){let s;t.iGM(s=t.CRH())&&(i.st=s.first)}},decls:7,vars:9,consts:[[3,"action"],["phActionTpl",""],["mode","search",3,"schema","formSubmit","formReset"],[3,"data","loading","res","columns"],["st",""],["nz-button","","nzType","primary",3,"click"]],template:function(e,i){if(1&e){const s=t.EpF();t.TgZ(0,"page-header",0),t.YNc(1,at,2,0,"ng-template",null,1,t.W1O),t.qZA(),t.TgZ(3,"nz-card")(4,"sf",2),t.NdJ("formSubmit",function(c){t.CHM(s);const u=t.MAs(6);return t.KtG(u.reset(c))})("formReset",function(c){t.CHM(s);const u=t.MAs(6);return t.KtG(u.reset(c))}),t.qZA(),t._UZ(5,"st",3,4),t.qZA()}if(2&e){const s=t.MAs(2);t.Q6J("action",s),t.xp6(4),t.Q6J("schema",i.searchSchema),t.xp6(1),t.Q6J("data",i.url)("loading",i.loading)("res",t.VKq(7,ct,t.DdM(6,rt)))("columns",i.columns)}},dependencies:[y.kJ,C.q,v.A5,h.ix,f.w,g.dQ,T.bd],encapsulation:2}),a})();function lt(n,a){1&n&&t._UZ(0,"nz-spin",4)}function ut(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"sf",5,6)(2,"div",7)(3,"button",8),t.NdJ("click",function(){t.CHM(o);const i=t.oxw();return t.KtG(i.close())}),t._uU(4,"\u5173\u95ed"),t.qZA(),t.TgZ(5,"button",9),t.NdJ("click",function(){t.CHM(o);const i=t.MAs(1),s=t.oxw();return t.KtG(s.save(i.value))}),t._uU(6,"\u4fdd\u5b58"),t.qZA()()()}if(2&n){const o=t.MAs(1),e=t.oxw();t.Q6J("schema",e.schema)("ui",e.ui)("formData",e.i),t.xp6(5),t.Q6J("disabled",!o.valid)("nzLoading",e.http.loading)}}let b=(()=>{var n;class a{constructor(e,i,s){this.modal=e,this.msgSrv=i,this.http=s,this.record={},this.schema={properties:{menuName:{type:"string",title:"\u83dc\u5355\u540d\u79f0",maxLength:15},menuCode:{type:"string",title:"\u83dc\u5355\u7f16\u7801"}},required:["menuName","menuCode"]},this.ui={"*":{spanLabelFixed:100,grid:{span:24}},$menuName:{widget:"string"}}}ngOnInit(){}save(e){this.http.post("/api/menu/update",e).subscribe(i=>{this.msgSrv.success(i.msg),this.modal.close(!0)})}close(){this.modal.destroy()}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(M.Lf),t.Y36(x.dD),t.Y36(d.lP))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-menu-edit"]],decls:5,vars:3,consts:[[1,"modal-header"],[1,"modal-title"],["class","modal-spin",4,"ngIf"],["mode","edit","button","none",3,"schema","ui","formData",4,"ngIf"],[1,"modal-spin"],["mode","edit","button","none",3,"schema","ui","formData"],["sf",""],[1,"modal-footer"],["nz-button","","type","button",3,"click"],["nz-button","","type","submit","nzType","primary",3,"disabled","nzLoading","click"]],template:function(e,i){1&e&&(t.TgZ(0,"div",0)(1,"div",1),t._uU(2),t.qZA()(),t.YNc(3,lt,1,0,"nz-spin",2),t.YNc(4,ut,7,5,"sf",3)),2&e&&(t.xp6(2),t.hij("\u7f16\u8f91 ",i.record.id," \u4fe1\u606f"),t.xp6(1),t.Q6J("ngIf",!i.i),t.xp6(1),t.Q6J("ngIf",i.i))},dependencies:[m.O5,y.kJ,h.ix,f.w,g.dQ,z.W],encapsulation:2}),a})();var S=l(95),I=l(824),dt=l(8373),pt=l(9035),p=l(3740),_t=l(6109),mt=l(551),ht=l(6987);function ft(n,a){1&n&&t._UZ(0,"nz-spin",4)}function gt(n,a){if(1&n&&(t.ynx(0),t.TgZ(1,"div",19),t._uU(2),t.qZA(),t.BQk()),2&n){const o=t.oxw().$implicit;t.xp6(1),t.s9C("nzTooltipTitle",o.authName),t.xp6(1),t.hij(" ",o.authName," ")}}function yt(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"i",24),t.NdJ("click",function(){t.CHM(o);const i=t.oxw(3).$implicit,s=t.oxw(2);return t.KtG(s.editCache[i.authId].data.authName=null)}),t.qZA()}}function xt(n,a){if(1&n&&t.YNc(0,yt,1,0,"i",23),2&n){const o=t.oxw(2).$implicit,e=t.oxw(2);t.Q6J("ngIf",e.editCache[o.authId].data.authName)}}function Ct(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"nz-input-group",20)(1,"input",21),t.NdJ("ngModelChange",function(i){t.CHM(o);const s=t.oxw().$implicit,r=t.oxw(2);return t.KtG(r.editCache[s.authId].data.authName=i)}),t.qZA()(),t.YNc(2,xt,1,1,"ng-template",null,22,t.W1O)}if(2&n){const o=t.MAs(3),e=t.oxw().$implicit,i=t.oxw(2);t.Q6J("nzSuffix",o),t.xp6(1),t.Q6J("maxLength",80)("ngModel",i.editCache[e.authId].data.authName)}}function Tt(n,a){if(1&n&&(t.ynx(0),t.TgZ(1,"div",19),t._uU(2),t.qZA(),t.BQk()),2&n){const o=t.oxw().$implicit;t.xp6(1),t.s9C("nzTooltipTitle",o.authValue),t.xp6(1),t.hij(" ",o.authValue," ")}}function Mt(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"i",24),t.NdJ("click",function(){t.CHM(o);const i=t.oxw(3).$implicit,s=t.oxw(2);return t.KtG(s.editCache[i.authId].data.authValue=null)}),t.qZA()}}function zt(n,a){if(1&n&&t.YNc(0,Mt,1,0,"i",23),2&n){const o=t.oxw(2).$implicit,e=t.oxw(2);t.Q6J("ngIf",e.editCache[o.authId].data.authValue)}}function St(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"nz-input-group",20)(1,"input",25),t.NdJ("ngModelChange",function(i){t.CHM(o);const s=t.oxw().$implicit,r=t.oxw(2);return t.KtG(r.editCache[s.authId].data.authValue=i)}),t.qZA()(),t.YNc(2,zt,1,1,"ng-template",null,26,t.W1O)}if(2&n){const o=t.MAs(3),e=t.oxw().$implicit,i=t.oxw(2);t.Q6J("nzSuffix",o),t.xp6(1),t.Q6J("maxLength",80)("ngModel",i.editCache[e.authId].data.authValue)}}function vt(n,a){if(1&n&&(t.ynx(0),t.TgZ(1,"div",19),t._uU(2),t.qZA(),t.BQk()),2&n){const o=t.oxw().$implicit;t.xp6(1),t.s9C("nzTooltipTitle",o.displayIndex),t.xp6(1),t.hij(" ",o.displayIndex," ")}}function At(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"nz-input-number",27),t.NdJ("ngModelChange",function(i){t.CHM(o);const s=t.oxw().$implicit,r=t.oxw(2);return t.KtG(r.editCache[s.authId].data.displayIndex=i)}),t.qZA()}if(2&n){const o=t.oxw().$implicit,e=t.oxw(2);t.Q6J("nzMin",1)("ngModel",e.editCache[o.authId].data.displayIndex)}}function Zt(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"a",31),t.NdJ("nzOnConfirm",function(){t.CHM(o);const i=t.oxw(2).$implicit,s=t.oxw(2);return t.KtG(s.recordDel(i.authId))}),t._uU(1," \u5220\u9664 "),t.qZA()}}function bt(n,a){if(1&n){const o=t.EpF();t.ynx(0),t.TgZ(1,"a",28),t.NdJ("click",function(){t.CHM(o);const i=t.oxw().$implicit,s=t.oxw(2);return t.KtG(s.startEdit(i.authId))}),t._uU(2,"\u7f16\u8f91"),t.qZA(),t._UZ(3,"nz-divider",29),t.YNc(4,Zt,2,0,"a",30),t.BQk()}if(2&n){const o=t.oxw().$implicit,e=t.oxw(2);t.xp6(4),t.Q6J("ngIf",!e.editCache[o.authId].edit)}}function It(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"a",28),t.NdJ("click",function(){t.CHM(o);const i=t.oxw().$implicit,s=t.oxw(2);return t.KtG(s.saveEdit(i.authId))}),t._uU(1,"\u4fdd\u5b58"),t.qZA(),t._UZ(2,"nz-divider",29),t.TgZ(3,"a",28),t.NdJ("click",function(){t.CHM(o);const i=t.oxw().$implicit,s=t.oxw(2);return t.KtG(s.cancelEdit(i))}),t._uU(4,"\u53d6\u6d88"),t.qZA()}}function Nt(n,a){if(1&n&&(t.TgZ(0,"tr")(1,"td"),t._uU(2),t.qZA(),t.TgZ(3,"td"),t.YNc(4,gt,3,2,"ng-container",13),t.YNc(5,Ct,4,3,"ng-template",null,14,t.W1O),t.qZA(),t.TgZ(7,"td"),t.YNc(8,Tt,3,2,"ng-container",13),t.YNc(9,St,4,3,"ng-template",null,15,t.W1O),t.qZA(),t.TgZ(11,"td"),t.YNc(12,vt,3,2,"ng-container",13),t.YNc(13,At,1,2,"ng-template",null,16,t.W1O),t.qZA(),t.TgZ(15,"td",17),t.YNc(16,bt,5,1,"ng-container",13),t.YNc(17,It,5,0,"ng-template",null,18,t.W1O),t.qZA()()),2&n){const o=a.$implicit,e=a.index,i=t.MAs(6),s=t.MAs(10),r=t.MAs(14),c=t.MAs(18),u=t.oxw(2);t.xp6(2),t.Oqu(e+1),t.xp6(2),t.Q6J("ngIf",!u.editCache[o.authId].edit)("ngIfElse",i),t.xp6(4),t.Q6J("ngIf",!u.editCache[o.authId].edit)("ngIfElse",s),t.xp6(4),t.Q6J("ngIf",!u.editCache[o.authId].edit)("ngIfElse",r),t.xp6(4),t.Q6J("ngIf",!u.editCache[o.authId].edit)("ngIfElse",c)}}function wt(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"div")(1,"nz-table",5,6)(3,"thead")(4,"tr")(5,"th",7),t._uU(6,"\u5e8f\u53f7"),t.qZA(),t.TgZ(7,"th",8),t._uU(8,"\u6743\u9650\u540d\u79f0"),t.qZA(),t.TgZ(9,"th",8),t._uU(10,"\u6743\u9650\u4ee3\u53f7"),t.qZA(),t.TgZ(11,"th",7),t._uU(12,"\u6392\u5e8f"),t.qZA(),t.TgZ(13,"th",9),t._uU(14,"\u64cd\u4f5c"),t.qZA()()(),t.TgZ(15,"tbody"),t.YNc(16,Nt,19,9,"tr",10),t.qZA()(),t.TgZ(17,"button",11),t.NdJ("click",function(){t.CHM(o);const i=t.oxw();return t.KtG(i.addListData())}),t._UZ(18,"i",12),t._uU(19,"\u6dfb\u52a0\u6743\u9650 "),t.qZA()()}if(2&n){const o=t.MAs(2),e=t.oxw();t.xp6(1),t.Q6J("nzShowPagination",!1)("nzFrontPagination",!1)("nzTotal",e.powerList.length)("nzData",e.powerList)("nzSize","small")("nzLoading",e.listLoading),t.xp6(15),t.Q6J("ngForOf",o.data)}}let L=(()=>{var n;class a{constructor(e,i,s,r,c){this.modal=e,this.msgSrv=i,this.http=s,this.fb=r,this.ms=c,this.powerList=[],this.editCache={},this.listLoading=!1}ngOnInit(){this.menuId=this.i.menuId,this.loadData()}loadData(){this.http.get(`/api/auth/all?menuId=${this.menuId}`).subscribe(e=>{this.powerList=e.data,this.updateEditCache()})}startEdit(e){this.editCache[e].edit=!0}cancelEdit(e){const i=this.powerList.findIndex(s=>s.authId===e);this.powerList.splice(i,1),this.powerList=[...this.powerList]}saveEdit(e){if(!this.checkNullData(e))return;this.listLoading=!0;const i=this.powerList.findIndex(c=>c.authId===e);Object.assign(this.powerList[i],this.editCache[e].data),this.editCache[e].edit=!1;const s=this.editCache[e].data;this.http.post(null==s.authId?"/api/auth/insert":"/api/auth/update",{authId:s.authId,authName:s.authName,authValue:s.authValue,displayIndex:s.displayIndex,menuId:this.menuId}).subscribe(c=>{this.msgSrv.success(c.msg),this.loadData(),this.listLoading=!1})}checkNullData(e){return null===this.editCache[e].data.authName||""===this.editCache[e].data.authName?(this.ms.warning("\u8bf7\u586b\u5199\u6743\u9650\u540d\u79f0\uff01"),!1):null!==this.editCache[e].data.authValue&&""!==this.editCache[e].data.authValue||(this.ms.warning("\u8bf7\u586b\u5199\u6743\u9650\u4ee3\u53f7\uff01"),!1)}updateEditCache(){this.powerList.forEach(e=>{this.editCache[e.authId]={edit:!1,data:{...e}}})}recordDel(e){this.http.post(`/api/auth/delete/${e}`,{}).subscribe(i=>{this.ms.success(i.msg),this.loadData()})}addListData(){this.powerList=[...this.powerList,{id:"__random__"+Math.random(),name:"",value:"",isNew:!0}],this.powerList.forEach((e,i)=>{this.editCache[e.authId]={edit:e.isNew,data:{...e}}})}close(){this.modal.destroy()}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(M.Lf),t.Y36(x.dD),t.Y36(d.lP),t.Y36(S.qu),t.Y36(x.dD))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-menu-auth"]],decls:5,vars:2,consts:[[1,"modal-header"],[1,"modal-title"],["class","modal-spin",4,"ngIf"],[4,"ngIf"],[1,"modal-spin"],[1,"yz-editable-table",3,"nzShowPagination","nzFrontPagination","nzTotal","nzData","nzSize","nzLoading"],["editRowTable2",""],["nzWidth","50px"],["nzWidth","150px"],["nzWidth","80px"],[4,"ngFor","ngForOf"],["nz-button","","nzType","dashed","nzBlock","",2,"margin-top","16px",3,"click"],["nz-icon","","nzType","plus","nzTheme","outline"],[4,"ngIf","ngIfElse"],["nameInputTpl2",""],["valueInputTpl2",""],["valueInputTpl3",""],["nzRight","true"],["saveTpl2",""],["nz-tooltip","",3,"nzTooltipTitle"],[3,"nzSuffix"],["type","text","nz-input","","placeholder","\u6743\u9650\u540d\u79f0",3,"maxLength","ngModel","ngModelChange"],["nameSuffixTemplate2",""],["nz-icon","","nz-tooltip","","class","ant-input-clear-icon","nzTheme","fill","nzType","close-circle",3,"click",4,"ngIf"],["nz-icon","","nz-tooltip","","nzTheme","fill","nzType","close-circle",1,"ant-input-clear-icon",3,"click"],["type","text","nz-input","","placeholder","\u6743\u9650\u4ee3\u7801",3,"maxLength","ngModel","ngModelChange"],["valueSuffixTemplate2",""],["nz-input","","nzPlaceHolder","\u6392\u5e8f\u53f7",3,"nzMin","ngModel","ngModelChange"],[3,"click"],["nzType","vertical"],["nz-popconfirm","","nzPopconfirmTitle","\u662f\u5426\u8981\u5220\u9664\u6b64\u884c?",3,"nzOnConfirm",4,"ngIf"],["nz-popconfirm","","nzPopconfirmTitle","\u662f\u5426\u8981\u5220\u9664\u6b64\u884c?",3,"nzOnConfirm"]],template:function(e,i){1&e&&(t.TgZ(0,"div",0)(1,"div",1),t._uU(2,"\u7f16\u8f91\u6743\u9650\u4fe1\u606f"),t.qZA()(),t.YNc(3,ft,1,0,"nz-spin",2),t.YNc(4,wt,20,7,"div",3)),2&e&&(t.xp6(3),t.Q6J("ngIf",!i.i),t.xp6(1),t.Q6J("ngIf",i.i))},dependencies:[m.sg,m.O5,S.Fj,S.JJ,S.On,h.ix,f.w,g.dQ,I.Zp,I.gB,I.ke,dt._V,pt.JW,p.N8,p.Uo,p._C,p.Om,p.p0,p.$Z,p.qn,_t.SY,mt.Ls,z.W,ht.g],encapsulation:2}),a})();function Et(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"button",6),t.NdJ("click",function(){t.CHM(o);const i=t.oxw();return t.KtG(i.add())}),t._uU(1,"\u65b0\u5efa"),t.qZA()}}function Ft(n,a){if(1&n){const o=t.EpF();t.TgZ(0,"tr")(1,"td",8),t.NdJ("nzExpandChange",function(i){t.CHM(o);const s=t.oxw().$implicit;return t.KtG(s.expand=i)})("nzExpandChange",function(i){t.CHM(o);const s=t.oxw().$implicit,r=t.oxw().$implicit,c=t.oxw();return t.KtG(c.collapse(c.mapOfExpandedData[r.menuId],s,i))}),t._uU(2),t.qZA(),t.TgZ(3,"td"),t._uU(4),t.qZA(),t.TgZ(5,"td"),t._uU(6),t.qZA(),t.TgZ(7,"td"),t._uU(8),t.qZA(),t.TgZ(9,"td")(10,"a",9),t.NdJ("click",function(){t.CHM(o);const i=t.oxw().$implicit,s=t.oxw(2);return t.KtG(s.edit(i))}),t._uU(11,"\u7f16\u8f91"),t.qZA(),t.TgZ(12,"a",9),t.NdJ("click",function(){t.CHM(o);const i=t.oxw().$implicit,s=t.oxw(2);return t.KtG(s.authMgmt(i))}),t._uU(13,"\u6743\u9650\u7ba1\u7406"),t.qZA(),t.TgZ(14,"a",10),t.NdJ("click",function(){t.CHM(o);const i=t.oxw().$implicit,s=t.oxw(2);return t.KtG(s.delete(i))}),t._uU(15,"\u5220\u9664"),t.qZA()()()}if(2&n){const o=t.oxw().$implicit;t.xp6(1),t.Q6J("nzIndentSize",20*o.level)("nzShowExpand",!!o.children&&o.children.length>0)("nzExpand",o.expand),t.xp6(1),t.hij(" ",o.menuName," "),t.xp6(2),t.Oqu(o.menuCode),t.xp6(2),t.Oqu(o.link),t.xp6(2),t.Oqu(o.icon)}}function Jt(n,a){if(1&n&&(t.ynx(0),t.YNc(1,Ft,16,7,"tr",7),t.BQk()),2&n){const o=a.$implicit;t.xp6(1),t.Q6J("ngIf",o.parent&&o.parent.expand||!o.parent)}}function kt(n,a){if(1&n&&(t.ynx(0),t.YNc(1,Jt,2,1,"ng-container",5),t.BQk()),2&n){const o=a.$implicit,e=t.oxw();t.xp6(1),t.Q6J("ngForOf",e.mapOfExpandedData[o.menuId])}}const Ut=[{path:"user",component:J},{path:"role",component:D},{path:"menu",component:(()=>{var n;class a{constructor(e,i){this.http=e,this.modal=i,this.searchSchema={properties:{no:{type:"string",title:"\u7f16\u53f7"}}},this.loading=!1,this.listOfMapData=[],this.mapOfExpandedData={}}ngOnInit(){this.queryTree()}queryTree(){this.loading=!0,this.http.get("/api/menu/tree").subscribe(e=>{this.listOfMapData=e.data,this.listOfMapData.forEach(i=>{this.mapOfExpandedData[i.menuId]=this.convertTreeToList(i)}),this.loading=!1})}collapse(e,i,s){if(!s){if(!i.children)return;i.children.forEach(r=>{const c=e.find(u=>u.menuId===r.menuId);c.expand=!1,this.collapse(e,c,!1)})}}convertTreeToList(e){const i=[],s=[],r={};for(i.push({...e,level:0,expand:!1});0!==i.length;){const c=i.pop();if(this.visitNode(c,r,s),c.children)for(let u=c.children.length-1;u>=0;u--)i.push({...c.children[u],level:c.level+1,expand:!1,parent:c})}return s}visitNode(e,i,s){i[e.menuId]||(i[e.menuId]=!0,s.push(e))}add(){this.modal.createStatic(b,{i:{menuId:null}}).subscribe(()=>this.queryTree())}search(e){this.queryTree()}reset(e){this.queryTree()}edit(e){this.modal.createStatic(b,{i:e}).subscribe(()=>this.queryTree())}authMgmt(e){this.modal.createStatic(L,{i:e}).subscribe(()=>this.queryTree())}delete(e){}}return(n=a).\u0275fac=function(e){return new(e||n)(t.Y36(d.lP),t.Y36(d.Te))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-sys-menu"]],decls:21,vars:5,consts:[[3,"action"],["phActionTpl",""],["mode","search",3,"schema","formSubmit","formReset"],["nzTableLayout","fixed",3,"nzLoading","nzData"],["expandTable",""],[4,"ngFor","ngForOf"],["nz-button","","nzType","primary",3,"click"],[4,"ngIf"],[3,"nzIndentSize","nzShowExpand","nzExpand","nzExpandChange"],["nz-button","","nzType","link",3,"click"],["nz-button","","nzType","link","nzDanger","",3,"click"]],template:function(e,i){if(1&e&&(t.TgZ(0,"page-header",0),t.YNc(1,Et,2,0,"ng-template",null,1,t.W1O),t.qZA(),t.TgZ(3,"nz-card")(4,"sf",2),t.NdJ("formSubmit",function(r){return i.search(r)})("formReset",function(r){return i.reset(r)}),t.qZA(),t.TgZ(5,"nz-table",3,4)(7,"thead")(8,"tr")(9,"th"),t._uU(10,"\u83dc\u5355\u540d\u79f0"),t.qZA(),t.TgZ(11,"th"),t._uU(12,"\u83dc\u5355\u7f16\u7801"),t.qZA(),t.TgZ(13,"th"),t._uU(14,"\u94fe\u63a5"),t.qZA(),t.TgZ(15,"th"),t._uU(16,"\u56fe\u6807"),t.qZA(),t.TgZ(17,"th"),t._uU(18,"\u64cd\u4f5c"),t.qZA()()(),t.TgZ(19,"tbody"),t.YNc(20,kt,2,1,"ng-container",5),t.qZA()()()),2&e){const s=t.MAs(2),r=t.MAs(6);t.Q6J("action",s),t.xp6(4),t.Q6J("schema",i.searchSchema),t.xp6(1),t.Q6J("nzLoading",i.loading)("nzData",i.listOfMapData),t.xp6(15),t.Q6J("ngForOf",r.data)}},dependencies:[m.sg,m.O5,y.kJ,C.q,h.ix,f.w,g.dQ,T.bd,p.N8,p.Uo,p._C,p.h7,p.Om,p.p0,p.$Z],encapsulation:2}),a})()},{path:"log",component:F}];let Dt=(()=>{var n;class a{}return(n=a).\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({imports:[w.Bz.forChild(Ut),w.Bz]}),a})(),Lt=(()=>{var n;class a{}return(n=a).\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({providers:[k],imports:[q.m8,Dt]}),a})()}}]);