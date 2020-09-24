<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 02/11/2018
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu S. Manager</title>
</head>
<body>
<% if (!((boolean) request.getSession().getAttribute("loggedIn"))) response.sendRedirect("home.jsp"); %>
    <br><a href="addManager.jsp">Add Manager</a>
    <br><a href="removeManager">Remove Manager</a>
    <br><a href="ServletLogout">Logout</a>
</body>
</html>
