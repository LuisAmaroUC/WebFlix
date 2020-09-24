package servlet;

import auxPack.movieS;
import auxPack.userServices;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/apagarMovies")
public class ServletApagarMovies extends HttpServlet {

    @EJB
    movieS delete;
    @EJB
    userServices check;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        boolean checkManager = check.checkType(request.getSession().getAttribute("email").toString());

        try {
            if ((boolean) request.getSession().getAttribute("loggedIn")) {
                if (checkManager) {
                    boolean flag = delete.delete(Integer.parseInt(request.getParameter("id")));
                    if (flag) response.sendRedirect("menuManagers.jsp");
                    else {

                        response.setContentType("text/html");
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('404 NOT FOUND');");
                        out.println("window.location.href = \"/project2-code-web/deleteMovie\"");
                        out.println("</script>");
                    }
                }   else {

                    response.setContentType("text/html");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('You´re Not Manager');");
                    out.println("window.location.href = \"home.jsp\"");
                    out.println("</script>");
                }
            } else {

                response.setContentType("text/html");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('You´re Not Loggedin');");
                out.println("window.location.href = \"home.jsp\"");
                out.println("</script>");
            }
        } catch(Exception e){
        response.setContentType("text/html");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Input Incorreto! Escreva apenas inteiros');");
        out.println("window.location.href = \"/project2-code-web/deleteMovie\"");
        out.println("</script>");
    }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
