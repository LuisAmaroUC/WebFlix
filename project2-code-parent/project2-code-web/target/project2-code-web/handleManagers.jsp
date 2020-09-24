<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 02/11/2018
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Secret Page</title>
</head>
<body>

<form id="1" action="HandleManagers" method="POST">
    <table border="1" align="center">
        <tr>
            <td>Email :</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>



            <center><td><input type="submit" value="submit"/></td></center>
        </tr>


    </table>
    <center><br><a href="home.jsp">Ir para a aplicação</a></center>
</form>
</body>
</html>
