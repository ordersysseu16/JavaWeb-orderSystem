# orderSys

> A JavaWeb project

## 项目涉及到技术栈：
- SSM：Spring、SpringMVC、Mybatis等
- MVC：Model、View、Controller的架构风格
- 前后端分离：Vue+jsp+ajax
- 第三方模块：element_ui、jquery、hibernate validator
- 基础开发环境和包管理：JDK_1.7、mysql_5.56、Tomcat_7.0
- 构建工具：Eclipse
- 编辑器：Eclipse、Webstrom等

## 项目功能
**供餐厅管理的web项目，入口分为管理员与后厨两个，对接子项目orderSys_mobile，共同完成餐厅从点餐、后厨分配、传菜、结账到信息管理的整个流程**

1. 后台管理员页面
管理员通过指定的管理员入口，用账号密码登录系统进行信息管理，对职工与菜品增删改查，对账单的明细查看和业绩统计
2. 后厨页面
后厨厨师获得服务器分配的点餐信息到每个人的列表中，成菜后点击完成将菜推入待传列表-->与子项目对接

## 渐进式
**餐厅管理者根据需要对此项目进行部署，使用部分或全部功能来稳定安全的运转餐厅**

1. 仅管理信息
可不使用子项目，仅使用管理功能进行简单的餐厅的信息管理业务
2. 完成简单的点餐与信息管理
使用子项目，利用点餐客户端完成点餐，仅向后厨输出点餐信息，烹饪完成后用传统传菜方式进行传菜，结算完成信息记录
3. 使用全部项目完成整个流程
在2的基础上，通过服务器的自动分配，每个厨师完成烹饪，进而服务员能够看到并接受待传菜信息完成更快的传菜

## 部署方法

1. 安装JRE_7
2. 安装mysql_5.5.6
3. 创建数据库orderSys
4. 运行koo.sql文件于此数据库
5. 将orderSys.war文件放置于Tomcat/webapps文件夹下
6. 运行Tomcat/bin/startup.bat
7. 分别访问http://localhost:8080/orderSys/admin与http://localhost:8080/orderSys/cook以确认成功
