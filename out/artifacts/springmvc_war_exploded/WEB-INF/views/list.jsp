<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/18
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="application/javascript" src="/js/jquery.min.js"></script>
<script type="application/javascript">
    $(function () {
      $(".delete").click(function () {
          var href =$(this).attr("href");
          $("form").attr("action",href).submit();
      })
    })
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="" method="post">
        <input type="hidden" name="_method" value="DELETE">
    </form>

    <c:if test="${empty requestScope.employees}">
        没有任何员工信息
    </c:if>

    <c:if test="${!empty requestScope.employees }">
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Department</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${requestScope.employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.gender ==0 ? 'Female':'Male'}</td>
                    <td>${emp.department.departmentName}</td>
                    <td><a href="/emp/${emp.id}">Edit</a> </td>
                    <td><a href="/emp/${emp.id}" class="delete">Delete</a> </td>
                </tr>

            </c:forEach>

        </table>
    </c:if>


    <br><br>

    <a href="/emp">Add New Department</a>
</body>
</html>
