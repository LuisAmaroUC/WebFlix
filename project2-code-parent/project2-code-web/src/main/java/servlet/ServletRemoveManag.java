package servlet;

import auxPack.managerServices;
import auxPack.superManagerServices;
import auxPack.userServices;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/removeManag")
public class ServletRemoveManag extends HttpServlet {

    @EJB
    managerServices remove;
    @EJB
    managerServices check;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        boolean checkManager = check.checkManagerType(request.getSession().getAttribute("email").toString());
        if((boolean)request.getSession().getAttribute("loggedIn")) {
            if (checkManager){
                boolean flag = remove.removeManager(request.getParameter("email"));
                if (flag) {
                    response.sendRedirect("menuSuperManager.jsp");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('404 not found');");
                    out.println("window.location.href = \"/project2-code-web/removeManager\"");
                    out.println("</script>");
                }
            }else  {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Not Allowed en here');");
                out.println("window.location.href = \"home.jsp\"");
                out.println("</script>");
            }
        }else{
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Not loggedIn');");
            out.println("window.location.href = \"handleManagers.jsp\"");
            out.println("</script>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
