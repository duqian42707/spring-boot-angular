"use strict";(self.webpackChunkportal=self.webpackChunkportal||[]).push([[111],{2111:(O,y,c)=>{c.r(y),c.d(y,{ExceptionModule:()=>r});var h=c(6895),t=c(4650),z=c(7579),f=c(2722),k=c(8797),m=c(9094),D=c(1481),M=c(4913),Z=c(445),v=c(9643),l=c(9132),u=c(6616),T=c(7044),C=c(1811);const J=["conTpl"];function L(o,e){if(1&o&&(t.TgZ(0,"button",9),t._uU(1),t.qZA()),2&o){const n=t.oxw();t.Q6J("routerLink",n.backRouterLink)("nzType","primary"),t.xp6(1),t.hij(" ",n.locale.backToHome," ")}}const R=["*"];let b=(()=>{class o{set type(n){const i=this.typeDict[n];i&&(this.fixImg(i.img),this._type=n,this._title=i.title,this._desc="")}fixImg(n){this._img=this.dom.bypassSecurityTrustStyle(`url('${n}')`)}set img(n){this.fixImg(n)}set title(n){this._title=this.dom.bypassSecurityTrustHtml(n)}set desc(n){this._desc=this.dom.bypassSecurityTrustHtml(n)}checkContent(){this.hasCon=!(0,k.xb)(this.conTpl.nativeElement),this.cdr.detectChanges()}constructor(n,i,a,g,x){this.i18n=n,this.dom=i,this.directionality=g,this.cdr=x,this.destroy$=new z.x,this.locale={},this.hasCon=!1,this.dir="ltr",this._img="",this._title="",this._desc="",this.backRouterLink="/",a.attach(this,"exception",{typeDict:{403:{img:"https://gw.alipayobjects.com/zos/rmsportal/wZcnGqRDyhPOEYFcZDnb.svg",title:"403"},404:{img:"https://gw.alipayobjects.com/zos/rmsportal/KpnpchXsobRgLElEozzI.svg",title:"404"},500:{img:"https://gw.alipayobjects.com/zos/rmsportal/RVRUAYdCGeYNBWoKiIwB.svg",title:"500"}}})}ngOnInit(){this.dir=this.directionality.value,this.directionality.change?.pipe((0,f.R)(this.destroy$)).subscribe(n=>{this.dir=n}),this.i18n.change.pipe((0,f.R)(this.destroy$)).subscribe(()=>this.locale=this.i18n.getData("exception")),this.checkContent()}ngOnDestroy(){this.destroy$.next(),this.destroy$.complete()}}return o.\u0275fac=function(n){return new(n||o)(t.Y36(m.s7),t.Y36(D.H7),t.Y36(M.Ri),t.Y36(Z.Is,8),t.Y36(t.sBO))},o.\u0275cmp=t.Xpm({type:o,selectors:[["exception"]],viewQuery:function(n,i){if(1&n&&t.Gf(J,7),2&n){let a;t.iGM(a=t.CRH())&&(i.conTpl=a.first)}},hostVars:4,hostBindings:function(n,i){2&n&&t.ekj("exception",!0)("exception-rtl","rtl"===i.dir)},inputs:{type:"type",img:"img",title:"title",desc:"desc",backRouterLink:"backRouterLink"},exportAs:["exception"],ngContentSelectors:R,decls:10,vars:5,consts:[[1,"exception__img-block"],[1,"exception__img"],[1,"exception__cont"],[1,"exception__cont-title",3,"innerHTML"],[1,"exception__cont-desc",3,"innerHTML"],[1,"exception__cont-actions"],[3,"cdkObserveContent"],["conTpl",""],["nz-button","",3,"routerLink","nzType",4,"ngIf"],["nz-button","",3,"routerLink","nzType"]],template:function(n,i){1&n&&(t.F$t(),t.TgZ(0,"div",0),t._UZ(1,"div",1),t.qZA(),t.TgZ(2,"div",2),t._UZ(3,"h1",3)(4,"div",4),t.TgZ(5,"div",5)(6,"div",6,7),t.NdJ("cdkObserveContent",function(){return i.checkContent()}),t.Hsn(8),t.qZA(),t.YNc(9,L,2,3,"button",8),t.qZA()()),2&n&&(t.xp6(1),t.Udp("background-image",i._img),t.xp6(2),t.Q6J("innerHTML",i._title,t.oJD),t.xp6(1),t.Q6J("innerHTML",i._desc||i.locale[i._type],t.oJD),t.xp6(5),t.Q6J("ngIf",!i.hasCon))},dependencies:[h.O5,v.wD,l.rH,u.ix,T.w,C.dQ],encapsulation:2,changeDetection:0}),o})(),H=(()=>{class o{}return o.\u0275fac=function(n){return new(n||o)},o.\u0275mod=t.oAB({type:o}),o.\u0275inj=t.cJS({imports:[h.ez,v.Q8,l.Bz,m.lD,u.sL]}),o})();var E=c(1971);class s{get type(){return this.route.snapshot.data.type}constructor(e){this.route=e}}s.\u0275fac=function(e){return new(e||s)(t.Y36(l.gz))},s.\u0275cmp=t.Xpm({type:s,selectors:[["app-exception"]],decls:1,vars:1,consts:[[2,"min-height","500px","height","80%",3,"type"]],template:function(e,n){1&e&&t._UZ(0,"exception",0),2&e&&t.Q6J("type",n.type)},dependencies:[b],encapsulation:2,changeDetection:0});var Y=c(7128);function B(o,e){if(1&o){const n=t.EpF();t.TgZ(0,"button",3),t.NdJ("click",function(){const g=t.CHM(n).$implicit,x=t.oxw();return t.KtG(x.go(g))}),t._uU(1),t.qZA()}if(2&o){const n=e.$implicit;t.xp6(1),t.hij("\u89e6\u53d1",n,"")}}class d{constructor(e,n){this.http=e,this.tokenService=n,this.types=[401,403,404,500]}go(e){this.http.get(`/api/${e}`).subscribe()}refresh(){this.tokenService.set({token:"invalid-token"}),this.http.post("https://localhost:5001/auth").subscribe(e=>console.warn("\u6210\u529f",e),e=>{console.log("\u6700\u540e\u7ed3\u679c\u5931\u8d25",e)})}}d.\u0275fac=function(e){return new(e||d)(t.Y36(m.lP),t.Y36(Y.T))},d.\u0275cmp=t.Xpm({type:d,selectors:[["exception-trigger"]],decls:5,vars:1,consts:[[1,"pt-lg"],["nz-button","","nzDanger","",3,"click",4,"ngFor","ngForOf"],["nz-button","","nzType","link",3,"click"],["nz-button","","nzDanger","",3,"click"]],template:function(e,n){1&e&&(t.TgZ(0,"div",0)(1,"nz-card"),t.YNc(2,B,2,1,"button",1),t.TgZ(3,"button",2),t.NdJ("click",function(){return n.refresh()}),t._uU(4,"\u89e6\u53d1\u5237\u65b0Token"),t.qZA()()()),2&e&&(t.xp6(2),t.Q6J("ngForOf",n.types))},dependencies:[h.sg,u.ix,T.w,C.dQ,E.bd],encapsulation:2});const F=[{path:"403",component:s,data:{type:403}},{path:"404",component:s,data:{type:404}},{path:"500",component:s,data:{type:500}},{path:"trigger",component:d}];class p{}p.\u0275fac=function(e){return new(e||p)},p.\u0275mod=t.oAB({type:p}),p.\u0275inj=t.cJS({imports:[l.Bz.forChild(F),l.Bz]});class r{}r.\u0275fac=function(e){return new(e||r)},r.\u0275mod=t.oAB({type:r}),r.\u0275inj=t.cJS({imports:[h.ez,H,u.sL,E.vh,p]})}}]);