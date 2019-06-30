<%--
    本jsp页面是一个 装饰器页面: 最终组成的返回给浏览器的网页.
    原理:靠decorator标签将初始请求的页面内容 填装到此页面, 组成一个最终页面.

    显示各级菜单的页面
  Created by IntelliJ IDEA.
  User: liuyi
  Date: 2018/4/9
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
<head>
    <decorator:head/>
    <title>主页面这是 <decorator:title default="welcome you"/></title>
</head>
<body>
<center>
    <h3>这是添加了装饰的页面</h3>
    <strong><decorator:body/></strong>
</center>
</body>
</html>
