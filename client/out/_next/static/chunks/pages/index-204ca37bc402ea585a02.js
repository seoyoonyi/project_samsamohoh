(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[405],{7336:function(e,t,r){"use strict";r(809),r(9669)},6891:function(e,t,r){"use strict";r.r(t),r.d(t,{default:function(){return D}});var n=r(7294),a=r(1163),i=(r(7336),r(8889)),l=r(2982),c=r(4942),o=r(885),s=r(7462),d=r(4184),u=r.n(d),f=r(3381),p=function(e,t){var r={};for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&t.indexOf(n)<0&&(r[n]=e[n]);if(null!=e&&"function"===typeof Object.getOwnPropertySymbols){var a=0;for(n=Object.getOwnPropertySymbols(e);a<n.length;a++)t.indexOf(n[a])<0&&Object.prototype.propertyIsEnumerable.call(e,n[a])&&(r[n[a]]=e[n[a]])}return r},m=n.createContext({siderHook:{addSider:function(){return null},removeSider:function(){return null}}});function h(e){var t=e.suffixCls,r=e.tagName,a=e.displayName;return function(e){var i=function(a){var i=n.useContext(f.E_).getPrefixCls,l=a.prefixCls,c=i(t,l);return n.createElement(e,(0,s.Z)({prefixCls:c,tagName:r},a))};return i.displayName=a,i}}var v=function(e){var t=e.prefixCls,r=e.className,a=e.children,i=e.tagName,l=p(e,["prefixCls","className","children","tagName"]),c=u()(t,r);return n.createElement(i,(0,s.Z)({className:c},l),a)},x=h({suffixCls:"layout",tagName:"section",displayName:"Layout"})((function(e){var t,r=n.useContext(f.E_).direction,a=n.useState([]),i=(0,o.Z)(a,2),d=i[0],h=i[1],v=e.prefixCls,x=e.className,g=e.children,y=e.hasSider,N=e.tagName,w=p(e,["prefixCls","className","children","hasSider","tagName"]),C=u()(v,(t={},(0,c.Z)(t,"".concat(v,"-has-sider"),"boolean"===typeof y?y:d.length>0),(0,c.Z)(t,"".concat(v,"-rtl"),"rtl"===r),t),x);return n.createElement(m.Provider,{value:{siderHook:{addSider:function(e){h((function(t){return[].concat((0,l.Z)(t),[e])}))},removeSider:function(e){h((function(t){return t.filter((function(t){return t!==e}))}))}}}},n.createElement(N,(0,s.Z)({className:C},w),g))})),g=h({suffixCls:"layout-header",tagName:"header",displayName:"Header"})(v),y=h({suffixCls:"layout-footer",tagName:"footer",displayName:"Footer"})(v),N=h({suffixCls:"layout-content",tagName:"main",displayName:"Content"})(v),w=x,C=r(8423),E=r(1413),Z={icon:{tag:"svg",attrs:{viewBox:"0 0 1024 1024",focusable:"false"},children:[{tag:"path",attrs:{d:"M912 192H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 284H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 284H328c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h584c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zM104 228a56 56 0 10112 0 56 56 0 10-112 0zm0 284a56 56 0 10112 0 56 56 0 10-112 0zm0 284a56 56 0 10112 0 56 56 0 10-112 0z"}}]},name:"bars",theme:"outlined"},j=r(6988),b=function(e,t){return n.createElement(j.Z,(0,E.Z)((0,E.Z)({},e),{},{ref:t,icon:Z}))};b.displayName="BarsOutlined";var O=n.forwardRef(b),S={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M765.7 486.8L314.9 134.7A7.97 7.97 0 00302 141v77.3c0 4.9 2.3 9.6 6.1 12.6l360 281.1-360 281.1c-3.9 3-6.1 7.7-6.1 12.6V883c0 6.7 7.7 10.4 12.9 6.3l450.8-352.1a31.96 31.96 0 000-50.4z"}}]},name:"right",theme:"outlined"},k=function(e,t){return n.createElement(j.Z,(0,E.Z)((0,E.Z)({},e),{},{ref:t,icon:S}))};k.displayName="RightOutlined";var _=n.forwardRef(k),P={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M724 218.3V141c0-6.7-7.7-10.4-12.9-6.3L260.3 486.8a31.86 31.86 0 000 50.3l450.8 352.1c5.3 4.1 12.9.4 12.9-6.3v-77.3c0-4.9-2.3-9.6-6.1-12.6l-360-281 360-281.1c3.8-3 6.1-7.7 6.1-12.6z"}}]},name:"left",theme:"outlined"},z=function(e,t){return n.createElement(j.Z,(0,E.Z)((0,E.Z)({},e),{},{ref:t,icon:P}))};z.displayName="LeftOutlined";var F=n.forwardRef(z),H=function(e){return!isNaN(parseFloat(e))&&isFinite(e)},L=function(e,t){var r={};for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&t.indexOf(n)<0&&(r[n]=e[n]);if(null!=e&&"function"===typeof Object.getOwnPropertySymbols){var a=0;for(n=Object.getOwnPropertySymbols(e);a<n.length;a++)t.indexOf(n[a])<0&&Object.prototype.propertyIsEnumerable.call(e,n[a])&&(r[n[a]]=e[n[a]])}return r},R={xs:"479.98px",sm:"575.98px",md:"767.98px",lg:"991.98px",xl:"1199.98px",xxl:"1599.98px"},B=n.createContext({}),W=function(){var e=0;return function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return e+=1,"".concat(t).concat(e)}}(),M=n.forwardRef((function(e,t){var r=e.prefixCls,a=e.className,i=e.trigger,l=e.children,d=e.defaultCollapsed,p=void 0!==d&&d,h=e.theme,v=void 0===h?"dark":h,x=e.style,g=void 0===x?{}:x,y=e.collapsible,N=void 0!==y&&y,w=e.reverseArrow,E=void 0!==w&&w,Z=e.width,j=void 0===Z?200:Z,b=e.collapsedWidth,S=void 0===b?80:b,k=e.zeroWidthTriggerStyle,P=e.breakpoint,z=e.onCollapse,M=e.onBreakpoint,T=L(e,["prefixCls","className","trigger","children","defaultCollapsed","theme","style","collapsible","reverseArrow","width","collapsedWidth","zeroWidthTriggerStyle","breakpoint","onCollapse","onBreakpoint"]),A=(0,n.useContext)(m).siderHook,I=(0,n.useState)("collapsed"in T?T.collapsed:p),V=(0,o.Z)(I,2),X=V[0],q=V[1],D=(0,n.useState)(!1),G=(0,o.Z)(D,2),J=G[0],K=G[1];(0,n.useEffect)((function(){"collapsed"in T&&q(T.collapsed)}),[T.collapsed]);var Q=function(e,t){"collapsed"in T||q(e),null===z||void 0===z||z(e,t)},U=(0,n.useRef)();U.current=function(e){K(e.matches),null===M||void 0===M||M(e.matches),X!==e.matches&&Q(e.matches,"responsive")},(0,n.useEffect)((function(){function e(e){return U.current(e)}var t;if("undefined"!==typeof window){var r=window.matchMedia;if(r&&P&&P in R){t=r("(max-width: ".concat(R[P],")"));try{t.addEventListener("change",e)}catch(n){t.addListener(e)}e(t)}}return function(){try{null===t||void 0===t||t.removeEventListener("change",e)}catch(n){null===t||void 0===t||t.removeListener(e)}}}),[]),(0,n.useEffect)((function(){var e=W("ant-sider-");return A.addSider(e),function(){return A.removeSider(e)}}),[]);var Y=function(){Q(!X,"clickTrigger")},$=(0,n.useContext)(f.E_).getPrefixCls;return n.createElement(B.Provider,{value:{siderCollapsed:X}},function(){var e,o=$("layout-sider",r),d=(0,C.Z)(T,["collapsed"]),f=X?S:j,p=H(f)?"".concat(f,"px"):String(f),m=0===parseFloat(String(S||0))?n.createElement("span",{onClick:Y,className:u()("".concat(o,"-zero-width-trigger"),"".concat(o,"-zero-width-trigger-").concat(E?"right":"left")),style:k},i||n.createElement(O,null)):null,h={expanded:E?n.createElement(_,null):n.createElement(F,null),collapsed:E?n.createElement(F,null):n.createElement(_,null)}[X?"collapsed":"expanded"],x=null!==i?m||n.createElement("div",{className:"".concat(o,"-trigger"),onClick:Y,style:{width:p}},i||h):null,y=(0,s.Z)((0,s.Z)({},g),{flex:"0 0 ".concat(p),maxWidth:p,minWidth:p,width:p}),w=u()(o,"".concat(o,"-").concat(v),(e={},(0,c.Z)(e,"".concat(o,"-collapsed"),!!X),(0,c.Z)(e,"".concat(o,"-has-trigger"),N&&null!==i&&!m),(0,c.Z)(e,"".concat(o,"-below"),!!J),(0,c.Z)(e,"".concat(o,"-zero-width"),0===parseFloat(p)),e),a);return n.createElement("aside",(0,s.Z)({className:w},d,{style:y,ref:t}),n.createElement("div",{className:"".concat(o,"-children")},l),N||J&&m?x:null)}())}));M.displayName="Sider";var T=M,A=w;A.Header=g,A.Footer=y,A.Content=N,A.Sider=T;var I=A,V=(r(1162),r(5893)),X=function(){var e=I.Header;I.Footer,I.Sider,I.Content;return(0,V.jsx)(V.Fragment,{children:(0,V.jsx)(e,{children:(0,V.jsx)(i.Z,{type:"primary",onClick:function(){a.default.push("/login")},children:"\ub85c\uadf8\uc778"})})})},q=r(8529),D=function(){return(0,V.jsx)(V.Fragment,{children:(0,V.jsxs)("div",{className:"index-wrap",children:[(0,V.jsx)(X,{}),(0,V.jsx)(q.default,{})]})})}},1162:function(e,t,r){"use strict";r.r(t);var n=r(4078),a=r(8792),i=r(7219),l=r(8633),c=r(5893);t.default=function(){return(0,c.jsx)(c.Fragment,{children:(0,c.jsx)("div",{className:"wrap",children:(0,c.jsxs)(n.Z,{direction:"vertical",children:[(0,c.jsx)(a.Z.Password,{placeholder:"input password"}),(0,c.jsx)(a.Z.Password,{placeholder:"input password",iconRender:function(e){return e?(0,c.jsx)(i.Z,{}):(0,c.jsx)(l.Z,{})}})]})})})}},8529:function(e,t,r){"use strict";r.r(t);r(7294),r(1163),r(7336);var n=r(3989),a=r(5893);t.default=function(){return(0,a.jsxs)(a.Fragment,{children:[(0,a.jsx)("h1",{children:"\uba54\uc778 \ud398\uc774\uc9c0"}),(0,a.jsx)(n.default,{})]})}},3989:function(e,t,r){"use strict";r.r(t);r(7294);var n=r(5893);t.default=function(){return(0,n.jsxs)("div",{children:[(0,n.jsx)("button",{children:"\ucd5c\uc2e0"}),(0,n.jsx)("button",{children:"\uc778\uae30"}),(0,n.jsx)("div",{children:"\ucd5c\uc2e0\ub0b4\uc6a9"}),(0,n.jsx)("div",{children:"\uc778\uae30\ub0b4\uc6a9"})]})}},8581:function(e,t,r){(window.__NEXT_P=window.__NEXT_P||[]).push(["/",function(){return r(6891)}])}},function(e){e.O(0,[844,400,774,888,179],(function(){return t=8581,e(e.s=t);var t}));var t=e.O();_N_E=t}]);