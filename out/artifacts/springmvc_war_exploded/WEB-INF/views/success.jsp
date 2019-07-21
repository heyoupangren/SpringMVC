<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/16
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>HelloWorld</title>
    <meta charset="UTF-8">
</head>
<body>
    <h3>HelloWorld Page</h3>

    time:${requestScope.time}
    <br>

    names:${requestScope.names}

    <br>
    request user : ${requestScope.user}
    <br>
    session user : ${sessionScope.user}
    <br>

    request school : ${requestScope.school}
    <br>
    session school : ${sessionScope.school}
    <br>

    abc user :${requestScope.abc}
    <br>

    user user:${requestScope.user}
    <br>

    <fmt:message key="i18n.username"></fmt:message>
    <br>
    <fmt:message key="i18n.password"></fmt:message>
    <br>
</body>
</html>
