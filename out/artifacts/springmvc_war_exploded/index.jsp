<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/16
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>HelloWorld</title>
  </head>
  <body>
    <a href="helloworld">HelloWorld</a>

    <br/><br/>

    <a href="springmvc/testRequestMapping">testRequestMapping</a>

    <br/><br/>

    <form action="springmvc/testMethod" method="post">
      <input type="submit" value="submit">
    </form>

    <br/><br/>

    <a href="springmvc/testParamsAndHeaders?username=cwh&age=10">Test ParamsAndHeaders</a>

    <br/><br/>

    <a href="springmvc/testAntPath/hjkk/xyz">Test AntPath</a>

    <br/><br/>

    <a href="springmvc/testPathVariable/1">Test PathVariable</a>

    <br/><br/>



    <a href="springmvc/testRest/1">Test Rest Get</a>

    <br/><br/>

    <form action="springmvc/testRest" method="post">
      <input type="submit" value="TestRest POST">
    </form>

    <br/><br/>

    <form action="springmvc/testRest/1" method="post">
      <input type="hidden" name="_method" value="DELETE">
      <input type="submit" value="TestRest DELETE">
    </form>

    <br/><br/>

    <form action="springmvc/testRest/1" method="post">
      <input type="hidden" name="_method" value="PUT">
      <input type="submit" value="TestRest PUT">
    </form>

    <br/><br/>

  <a href="/springmvc/testRequestParam?username=Tom&age=10">Test RequestParam</a>


    <br/><br/>

    <a href="/springmvc/testRequestHeader">Test RequestHeader</a>


    <br/><br/>

    <a href="/springmvc/testCookieValue">Test CookieValue</a>


    <br/><br/>

  <form action="/springmvc/testPojo" method="post">
    username : <input type="text" name="username"><br/>
    password : <input type="password" name="password"><br/>
    age : <input type="text" name="age"><br/>
    email : <input type="text" name="email"><br/>
    city:<input type="text" name="address.city"><br/>
    province:<input type="text" name="address.province"><br/>

    <input type="submit" name="submit" value="submit">
  </form>

  <br><br>

  <a href="/springmvc/testServletAPI">Test  ServletAPI</a>

    <br><br>

    <a href="/springmvc/testModelAndView">Test  ModelAndView</a>

    <br><br>

    <a href="/springmvc/testMap">Test  Map</a>

    <br><br>

    <a href="/springmvc/testSessionAttribute">Test  SessionAttribute</a>

    <br><br>

    <!--
      模拟修改操作
        1.原始数据为：1，Tom，123456,15，tom@163.com
        2.密码不能被修改
        3.表单回显，模拟操作直接在表单上填写对象的属性值
    -->
  <form action="/springmvc/testModelAttribute" method="post">
    <input type="hidden" name="id" value="1"><br>
    username : <input type="text" name="username" value="Tom"><br>
    age : <input type="text" name="age" value="12"><br>
    email : <input type="text" name="email" value="tom@163.com"><br>
    <input type="submit" value="submit"><br>
  </form>

    <br><br>


    <a href="/springmvc/testViewAndViewResolver">Test ViewAndViewResolver</a>

    <br><br>

    <a href="/springmvc/testView">Test View</a>

    <br><br>

    <a href="/springmvc/testRedirect">Test Redirect</a>

    <br><br>

    <a href="/springmvc/testForward">Test Forward</a>

    <br><br>

    <a href="/emps">Test Employee</a>
    <br>
  </body>
</html>
