"use strict";(self.webpackChunkportal=self.webpackChunkportal||[]).push([[526],{9526:(B,h,c)=>{c.r(h),c.d(h,{ExceptionModule:()=>H});var p=c(9808),t=c(5e3),T=c(7579),g=c(2722),C=c(5442),a=c(4447),E=c(2313),b=c(226),l=c(6042),m=c(7144),x=c(2643),y=c(2683),r=c(4521);const z=["conTpl"];function k(n,i){if(1&n&&(t.TgZ(0,"button",9),t._uU(1),t.qZA()),2&n){const e=t.oxw();t.Q6J("routerLink",e.backRouterLink)("nzType","primary"),t.xp6(1),t.hij(" ",e.locale.backToHome," ")}}const M=["*"];let f=(()=>{class n{constructor(e,o,s,d){this.i18n=e,this.dom=o,this.directionality=s,this.cdr=d,this.destroy$=new T.x,this.locale={},this.hasCon=!1,this.dir="ltr",this._img="",this._title="",this._desc="",this.backRouterLink="/"}set type(e){const o={403:{img:"https://gw.alipayobjects.com/zos/rmsportal/wZcnGqRDyhPOEYFcZDnb.svg",title:"403"},404:{img:"https://gw.alipayobjects.com/zos/rmsportal/KpnpchXsobRgLElEozzI.svg",title:"404"},500:{img:"https://gw.alipayobjects.com/zos/rmsportal/RVRUAYdCGeYNBWoKiIwB.svg",title:"500"}}[e];!o||(this.fixImg(o.img),this._type=e,this._title=o.title,this._desc="")}fixImg(e){this._img=this.dom.bypassSecurityTrustStyle(`url('${e}')`)}set img(e){this.fixImg(e)}set title(e){this._title=this.dom.bypassSecurityTrustHtml(e)}set desc(e){this._desc=this.dom.bypassSecurityTrustHtml(e)}checkContent(){this.hasCon=!(0,C.xb)(this.conTpl.nativeElement),this.cdr.detectChanges()}ngOnInit(){var e;this.dir=this.directionality.value,null===(e=this.directionality.change)||void 0===e||e.pipe((0,g.R)(this.destroy$)).subscribe(o=>{this.dir=o}),this.i18n.change.pipe((0,g.R)(this.destroy$)).subscribe(()=>this.locale=this.i18n.getData("exception")),this.checkContent()}ngOnDestroy(){this.destroy$.next(),this.destroy$.complete()}}return n.\u0275fac=function(e){return new(e||n)(t.Y36(a.s7),t.Y36(E.H7),t.Y36(b.Is,8),t.Y36(t.sBO))},n.\u0275cmp=t.Xpm({type:n,selectors:[["exception"]],viewQuery:function(e,o){if(1&e&&t.Gf(z,7),2&e){let s;t.iGM(s=t.CRH())&&(o.conTpl=s.first)}},hostVars:4,hostBindings:function(e,o){2&e&&t.ekj("exception",!0)("exception-rtl","rtl"===o.dir)},inputs:{type:"type",img:"img",title:"title",desc:"desc",backRouterLink:"backRouterLink"},exportAs:["exception"],ngContentSelectors:M,decls:10,vars:5,consts:[[1,"exception__img-block"],[1,"exception__img"],[1,"exception__cont"],[1,"exception__cont-title",3,"innerHTML"],[1,"exception__cont-desc",3,"innerHTML"],[1,"exception__cont-actions"],[3,"cdkObserveContent"],["conTpl",""],["nz-button","",3,"routerLink","nzType",4,"ngIf"],["nz-button","",3,"routerLink","nzType"]],template:function(e,o){1&e&&(t.F$t(),t.TgZ(0,"div",0),t._UZ(1,"div",1),t.qZA(),t.TgZ(2,"div",2),t._UZ(3,"h1",3)(4,"div",4),t.TgZ(5,"div",5)(6,"div",6,7),t.NdJ("cdkObserveContent",function(){return o.checkContent()}),t.Hsn(8),t.qZA(),t.YNc(9,k,2,3,"button",8),t.qZA()()),2&e&&(t.xp6(1),t.Udp("background-image",o._img),t.xp6(2),t.Q6J("innerHTML",o._title,t.oJD),t.xp6(1),t.Q6J("innerHTML",o._desc||o.locale[o._type],t.oJD),t.xp6(5),t.Q6J("ngIf",!o.hasCon))},directives:[l.ix,m.wD,p.O5,x.dQ,y.w,r.rH],encapsulation:2,changeDetection:0}),n})(),D=(()=>{class n{}return n.\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({imports:[[p.ez,m.Q8,r.Bz,a.lD,l.sL]]}),n})();var v=c(7484);let u=(()=>{class n{constructor(e){this.route=e}get type(){return this.route.snapshot.data.type}}return n.\u0275fac=function(e){return new(e||n)(t.Y36(r.gz))},n.\u0275cmp=t.Xpm({type:n,selectors:[["app-exception"]],decls:1,vars:1,consts:[[2,"min-height","500px","height","80%",3,"type"]],template:function(e,o){1&e&&t._UZ(0,"exception",0),2&e&&t.Q6J("type",o.type)},directives:[f],encapsulation:2,changeDetection:0}),n})();var Z=c(54);function J(n,i){if(1&n){const e=t.EpF();t.TgZ(0,"button",3),t.NdJ("click",function(){const d=t.CHM(e).$implicit;return t.oxw().go(d)}),t._uU(1),t.qZA()}if(2&n){const e=i.$implicit;t.xp6(1),t.hij("\u89e6\u53d1",e,"")}}const L=[{path:"403",component:u,data:{type:403}},{path:"404",component:u,data:{type:404}},{path:"500",component:u,data:{type:500}},{path:"trigger",component:(()=>{class n{constructor(e,o){this.http=e,this.tokenService=o,this.types=[401,403,404,500]}go(e){this.http.get(`/api/${e}`).subscribe()}refresh(){this.tokenService.set({token:"invalid-token"}),this.http.post("https://localhost:5001/auth").subscribe(e=>console.warn("\u6210\u529f",e),e=>{console.log("\u6700\u540e\u7ed3\u679c\u5931\u8d25",e)})}}return n.\u0275fac=function(e){return new(e||n)(t.Y36(a.lP),t.Y36(Z.T))},n.\u0275cmp=t.Xpm({type:n,selectors:[["exception-trigger"]],decls:5,vars:1,consts:[[1,"pt-lg"],["nz-button","","nzDanger","",3,"click",4,"ngFor","ngForOf"],["nz-button","","nzType","link",3,"click"],["nz-button","","nzDanger","",3,"click"]],template:function(e,o){1&e&&(t.TgZ(0,"div",0)(1,"nz-card"),t.YNc(2,J,2,1,"button",1),t.TgZ(3,"button",2),t.NdJ("click",function(){return o.refresh()}),t._uU(4,"\u89e6\u53d1\u5237\u65b0Token"),t.qZA()()()),2&e&&(t.xp6(2),t.Q6J("ngForOf",o.types))},directives:[v.bd,p.sg,l.ix,x.dQ,y.w],encapsulation:2}),n})()}];let R=(()=>{class n{}return n.\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({imports:[[r.Bz.forChild(L)],r.Bz]}),n})(),H=(()=>{class n{}return n.\u0275fac=function(e){return new(e||n)},n.\u0275mod=t.oAB({type:n}),n.\u0275inj=t.cJS({imports:[[p.ez,D,l.sL,v.vh,R]]}),n})()}}]);