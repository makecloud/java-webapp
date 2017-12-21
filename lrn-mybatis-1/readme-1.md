# 【mybatis入门第一步——原始使用sqlsession】

## 第一步
- 1、设置mybatis的配置文件configuration.xml，将来放在classpath下
- 2、创建数据库表对应的实体类
- 3、建立实体类的映射文件user.xml

## 配置文件解释下：
- 1.configuration.xml 是mybatis读取，创建sessionFactory的，里面包含了数据库连接相关东西。还有Java实体类对应的别名，映射配置文件中的resultType可以使用这个别名。
- 2.configuration.xml 里面的mapper元素，是包含映射的实体类的xml配置文件。
- 3.在user-mapper.xml文件里面，主要是定义各种SQL语句，以及这些语句的参数，以及要返回的类型等。
- 4.mybatis根据user.xml生成运行时mapper实例，所有增删改方法，写在xml里，生成到mapper中，调用mapper的方法实现增删改数据库操作。
- 秉承一个Pojo对应一个表的思想,mybaits下一个mapper作为一个pojo类和一张表的增删改查操作媒介

### 开始测试
mapper是mybatis根据映射配置文件，在运行时生成的实例，这个实例以映射配置内容为模板生成的实例，mybatis对数据库的操作就是生成在这个mapper实例里。