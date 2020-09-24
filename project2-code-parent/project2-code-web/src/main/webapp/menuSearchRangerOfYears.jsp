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
    <title>Show Range Of Years</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="Templates/apagarUserTemplate.css" type="text/css" rel="stylesheet">

</head>
<body><center>
    <% if (!((boolean) request.getSession().getAttribute("loggedIn"))) response.sendRedirect("home.jsp"); %>
    <form id="1" action="ShowMoviesRangeOfYears" method="POST">
        <h2> <p class="bg-primary">Show Movies withing a range of years</p></h2>


        <tr>
            <select size="1" name="Option" class="custom-select">
                <option value ="0">ASCENDING ORDER</option>
                <option value ="1">DESCENDING ORDER</option>
                <td><input type="text" name="year1"/></td>
                <td><input type="text" name="year2"/></td>

            </select><br>
            <td><input type="submit" value="Submit"/></td>

        </tr>
        <br><a href="menu.jsp">Voltar ao Menu</a>
        <br><a href="ServletLogout">Logout</a>


    </form>

</center></body>
</html>
