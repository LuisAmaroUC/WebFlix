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

@WebServlet("/editarFilme")
public class ServletEditarFilme extends HttpServlet {
    @EJB
    movieS edit;
    @EJB
    userServices check;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean checkManager = check.checkType(request.getSession().getAttribute("email").toString());
            if ((boolean) request.getSession().getAttribute("loggedIn")) {
                if (checkManager) {
                    boolean flag = edit.edit(request.getParameter("title"), request.getParameter("type"), Double.parseDouble(request.getParameter("length")), request.getParameter("director"), Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("year")));
                    if (flag) response.sendRedirect("menuManagers.jsp");
                    else {
                        PrintWriter out = response.getWriter();
                        response.setContentType("text/html");
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('O id não existe');");
                        out.println("window.location.href = \"/project2-code-web/editarMovie\"");
                        out.println("</script>");
                    }

                } else {
                    response.sendRedirect("NotManager.jsp");
                }
            } else response.sendRedirect("notLogger.jsp");
        }catch(Exception e){
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Input Incorreto! Length é um double');");
            out.println("window.location.href = \"/project2-code-web/editarMovie\"");
            out.println("</script>");
         }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
