# 商场VIP消费管理系统
[![Languages](https://img.shields.io/github/languages/top/qian0817/VIP-Management-System)](https://github.com/qian0817/VIP-Management-System)
[![License](https://img.shields.io/github/license/qian0817/VIP-Management-System)](https://github.com/qian0817/VIP-Management-System/LICENSE.txt)
##
JAVA课程设计作业
## 开发环境
JDK1.8 +Maven 3.6.1
## JAVA 9+
因为JAVA9以后对于反射的一部分限制,所以在JAVA9以上的版本会产生以下警告
```
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.alee.utils.ReflectUtils (file:weblaf-core-x.x.x.jar) to method {method.name}
WARNING: Please consider reporting this to the maintainers of com.alee.utils.ReflectUtils
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```
详见 https://github.com/mgarin/weblaf 
## 功能
+ `用户管理` 对用户的帐号和密码进行合法性验证，登录成功则进入系统功能界面。
+ `商品信息录入` 可录入商品编号、名称、制造商、生产日期、价格、折扣率、库存、商品简介、备注,然后将对录入的信息保存至数据库。
+ `商品信息查询` 可根据商品编号或名称进行模糊查询，查询结果列出商品编号、名称、制造商、生产日期、价格、折扣率、库存、商品简介、备注。
+ `商品信息修改` 可录入已经录入的商品名称、制造商、生产日期、价格、折扣率、库存、商品简介、备注进行修改。
+ `VIP信息录入` 可对VIP用户的姓名、证件号、性别、手机号码、联系地址、邮编等信息进行输入，并自动将当期时间作为入会时间进行保存。
+ `VIP信息查询` 可根据VIP用户的姓名、证件号或手机号进行模糊查询，查询结果列出姓名、证件号、性别、手机号码、联系地址、邮编、入会时间。
+ `VIP信息修改` 可对已经录入的VIP用户的姓名、性别、手机号码、联系地址、邮编等信息进行修改。
+ `VIP消费购物记录登记` 可根据VIP用户的姓名、证件号或手机号查询用户的消费记录
+ `VIP消费购物记录查询` 可对用户的购物记录进行登记，保存至数据库。
