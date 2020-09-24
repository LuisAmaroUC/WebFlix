<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 02/11/2018
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Manager</title>
</head>
<body>
<% if (!((boolean) request.getSession().getAttribute("loggedIn"))) response.sendRedirect("home.jsp"); %>
<form id="1" action="addManager" method="POST">
    <table border="1" align="center">
        <tr>
            <td> Nome :</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td> Password :</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td> E-mail :</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td> Número de cartao de cidadao :</td>
            <td><input type="text" name="ccNumber"/></td>
        </tr>
        <tr>

            <td><input type="submit" value="Criar"/></td>


    </table>
    <center><br><a href="menuSuperManager.jsp">Voltar ao Menu</a></center>
    <center><br><a href="ServletLogout">Logout</a></center>
</form>

</body>
</html>
