package servlet;

import auxPack.managerServices;
import auxPack.userServices;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/removeMovies")
public class ServletRemoveMovies extends HttpServlet {
    @EJB
    managerServices remove;
    @EJB
    userServices check;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if((boolean)request.getSession().getAttribute("loggedIn")) {
                boolean flag = remove.removeMovies(Integer.parseInt(request.getParameter("id")), request.getSession().getAttribute("email").toString());
                if (flag) {
                    boolean userType = check.checkType( request.getSession().getAttribute("email").toString());
                    if(userType) response.sendRedirect("menuManagers.jsp");
                    else response.sendRedirect("menu.jsp");
                } else {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Insira o ID correto do filme');");
                    out.println("window.location.href = \"/project2-code-web/ShowUserMovies\"");
                    out.println("</script>");

                }
            } else response.sendRedirect("Not logged");
        }catch(Exception e) {

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Input Incorreto! Escreva apenas inteiros');");
            out.println("window.location.href = \"/project2-code-web/ShowUserMovies\"");
            out.println("</script>");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
