huludemo
========

Hulu框架的一个Demo演示项目

## 安装 ##
1. clone huludemo项目，执行`mvn tomcat7:run`既可启动项目

## 使用 ##
1. 访问 `http://localhost:8080/huludemo/user/create?name=bbb&pass=ccc` 会创建一个用户。
2. 访问 `http://localhost:8080/huludemo/listUser` 会以JSON格式列出刚才创建的用户。
