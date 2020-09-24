<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 29/10/2018
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Perfil</title>
</head>
<body>
<% if (!((boolean) request.getSession().getAttribute("loggedIn"))) response.sendRedirect("home.jsp"); %>
<form id="1" action="ServletEditUser" method="POST">
    <table border="1" align="center">
        <tr>
            <td> Nome :</td>
            <td><input type="text" name="name" value="${(empty name) ? 'default' : name}"/></td>
        </tr>
        <tr>
            <td> Credit Card Number :</td>
            <td><input type="text" name="ccNumber" value="${(empty ccNumber) ? 0 : ccNumber}"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Editar"/></td>
    </table>
    <center><br><a href="menuManagers.jsp">Voltar ao Menu</a></center>
    <center><br><a href="ServletLogout">Logout</a></center>
</form>
</body>
</html>
