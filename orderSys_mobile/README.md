# orderSys_mobile

> A Vue.js project

## 项目涉及到技术栈：
- vue：Vue、Vue-router、Vue-cli等
- 组件化：单Vue文件
- 模块化：ES6 Module
- 第三方模块：better-scroll 等
- 基础开发环境和包管理：Node、npm
- 构建工具：webpack
- 编辑器：webstrom

## 项目功能
**一款供餐厅服务员使用的集点菜、传菜和结账功能于一体移动端点餐APP**

1. 登录页面
进入APP后显示登录页面，用户可以输入帐号密码进行登录，登录成功后才能进行后序操作。
2. 点餐页面
点选商品后自动添加到购物车，并计算好总价，在商品列表、购物车列表和商品详情页都可以随意增减数目，此外左侧商品类型和右侧的商品相互关联，通过better-scroll插件滑动商品列表时，相应的商品分类也会跟着跳转。
3. 账单页面
通过点餐页面左上角图标点击或右划动作可以唤出左侧抽屉，在其中点击账单可以进入账单页面，在该页面可以选择桌号进行付款结账。
4. 传菜页面
在左侧抽屉中可以进入传菜页面，用户可以查看当前待传菜列表并选择传送，用户可以在自己的传菜列表中查看自己传菜的列表。

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
