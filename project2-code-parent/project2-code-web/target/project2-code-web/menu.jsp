<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 29/10/2018
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Menu</title>

    <!-- Bootstrap core CSS -->
    <link href="Templates/auxBootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="Templates/menuTemplate.css" rel="stylesheet">
</head>

<body>
<% if (!((boolean) request.getSession().getAttribute("loggedIn"))) response.sendRedirect("home.jsp"); %>

<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">


            <li>
                <a href="menuSearchAllMovie.jsp">Show All Content</a>
            </li>
            <li>
                <a href="ShowMovies">Show Random Content</a>
            </li>
            <li>
                <a href="menuSerachMoviesDirector.jsp">Show Movies By Director</a>
            </li>
            <li>
                <a href="menuSearchMoviesCategory.jsp">Show Movies By Category</a>
            </li>
            <li>
                <a href="menuSearchRangerOfYears.jsp">Show Movies Withing a Range of Years</a>
            </li>

            <li>
                <a href="ShowUserMovies">Show My WatchList</a>
            </li>
            <li>
                <a href="editarUser.jsp">Editar Perfil</a>
            </li>
            <li>
                <a href="apagarUser.jsp">Apagar Perfil</a>
            </li>
            <li>
                <a href="ServletLogout">Logout</a>
            </li>
        </ul>
    </div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <h1>WelCome To WebFlix</h1>
            <a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Toggle Menu</a>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<script src="Templates/jquery.min.js"></script>
<script src="Templates/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>


</body>
</html>
