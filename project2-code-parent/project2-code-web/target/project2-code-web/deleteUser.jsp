<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 30/10/2018
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Delete User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body><center>
<% if (!((boolean) request.getSession().getAttribute("loggedIn"))) response.sendRedirect("home.jsp"); %>
<form id="1" action="ServletDeleteUser" method="POST">
    <h2> <p class="bg-primary">Tem a certeza de que quer apagar o seu perfil?</p></h2>

    <center><tr>
        <select size="1" name="Option">
            <option value ="yesButton">Yes</option>
            <option value ="noButton">No</option>
        </select>
        <td><input type="submit" value="Submit"/></td>

    </tr></center>
    <center><br><a href="menuManagers.jsp">Voltar ao Menu</a></center>
    <center><br><a href="ServletLogout">Logout</a></center>


</form>

</center></body>
</html>
