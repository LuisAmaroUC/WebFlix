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

@WebServlet("/CreateMovies")
public class ServletCreateMovies extends HttpServlet {

    @EJB
    movieS create;
    @EJB
    userServices check;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            boolean checkManager = check.checkType(request.getSession().getAttribute("email").toString());
            if((boolean)request.getSession().getAttribute("loggedIn")) {
                if(checkManager) {
                    boolean flag = create.create(request.getParameter("title"), request.getParameter("type"), Double.parseDouble(request.getParameter("length")), request.getParameter("director"),Integer.parseInt(request.getParameter("year")));
                    if (flag)
                        response.sendRedirect("menuManagers.jsp");

                    else
                    {

                        response.setContentType("text/html");
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('O filme ja existe existe');");
                        out.println("window.location.href = \"/project2-code-web/deleteMovie\"");
                        out.println("</script>");
                    }
                }else {

                    response.setContentType("text/html");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('You´re Not Manager');");
                    out.println("window.location.href = \"home.jsp\"");
                    out.println("</script>");
                }
            }else {

                response.setContentType("text/html");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('You´re Not Loggedin');");
                out.println("window.location.href = \"home.jsp\"");
                out.println("</script>");
            }
        }catch(Exception e) {
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Cuidado com os tipos! Length é um double! Year é um inteiro');");
            out.println("window.location.href = \"createMovies.jsp\"");
            out.println("</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
