<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/19
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--
    1.为什么使用Form标签呢？
     可以快速的开发出表单页面，而已可以更方便的进行表单值的回显
    2.注意：
    可以通过ModelAttribute 属性指定绑定的模型属性，
    若没有指定该属性，则默认从request 域对象中读取command 的表单bean，如果该属性值也不存在，则会发生错误
-->

    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        <!--path 属性对应html表单标签的name属性值-->
        <c:if test="${employee.id ==null}">
        LastName：<form:input path="lastName"/>
        </c:if>
        <c:if test="${employee.id!=null}">
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT">
        </c:if>
        <br>
        Email:<form:input path="email"/>
        <br>
        <%
            Map<String,String> genders = new HashMap();
            genders.put("1","Male");
            genders.put("2","Female");

            request.setAttribute("genders",genders);
        %>
        Gender:
        <br>
        <form:radiobuttons path="gender" items="${genders}" delimiter="<br>"/>
        <br>
        Department:<form:select path="department"
                                items="${departments}" itemLabel="departmentName" itemValue="id"></form:select>
        <br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>
