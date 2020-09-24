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
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="Templates/editUserTemplate.css" type="text/css" rel="stylesheet">

</head>
<body><center>
    <% if (!((boolean) request.getSession().getAttribute("loggedIn"))) response.sendRedirect("home.jsp"); %>

    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="form_main">
                    <h4 class="heading"><strong>Edit </strong> User <span></span></h4>
                    <div class="form">
                        <form action="EditManager" method="POST" id="1">

                            <label class="text-uppercase">Name</label><br>
                            <input type="text" value="${(empty name) ? 'default' : name}" name="name" class="txt"><br>


                            <label class="text-uppercase">Credit Card Number</label><br>
                            <input type="text" value="${(empty ccNumber) ? 0 : ccNumber}" name="ccNumber" class="txt"><br>

                            <input type="submit" value="Editar" name="submit" class="txt2">


                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <br><a href="menuManagers.jsp">Voltar ao Menu</a>
    <br>
    <div class="container">
        <a href="ServletLogout">
            <img src="Templates/loogout.jpg">
        </a>
    </div>
</center></body>
</html>
