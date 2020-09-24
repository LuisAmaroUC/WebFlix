<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 03/11/2018
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Movie</title>
</head>
<body>
<% if (!((boolean) request.getSession().getAttribute("loggedIn"))) response.sendRedirect("home.jsp"); %>
<form id="1" action="editarMovie" method="POST">
    <table border="1" align="center">
        <tr>
            <td> Title :</td>
            <td><input type="text" name="title"/></td>
        </tr>
        <tr>
            <td> Director:</td>
            <td><input type="text" name="director"/></td>
        </tr>
        <tr>
            <td> Type :</td>
            <td><input type="text" name="type"/></td>
        </tr>
        <tr>
            <td> Length :</td>
            <td><input type="text" name="length"/></td>
        </tr>
        <tr>
            <td> Year :</td>
            <td><input type="text" name="year"/></td>
        </tr>
        <tr>

            <td><input type="submit" value="Criar"/></td>

    </table>

    <center><br><a href="menu.jsp">Voltar ao Menu</a></center>
    <center><br><a href="ServletLogout">Logout</a></center>
</form>

</body>
</html>
