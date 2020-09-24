package servlet;

import auxPack.loginServices;
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

@WebServlet("/HandleManagers")
public class ServletHandleManagers extends HttpServlet {

    @EJB
    loginServices login;
    @EJB
    managerServices check;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            boolean flag = login.login(request.getParameter("email"), request.getParameter("password"));
            boolean checkType = check.checkManagerType(request.getParameter("email"));
            if(checkType){
                if(flag){
                    request.getSession().setAttribute("email", request.getParameter("email"));
                    request.getSession().setAttribute("loggedIn", true);
                    response.sendRedirect("menuSuperManager.jsp");

                }
                else{
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('LOGIN INVÁLIDO');");
                    out.println("window.location.href = \"handleManagers.jsp\"");
                    out.println("</script>");


                }
            }else{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('YOU´re NOT allowed in HERE');");
            out.println("window.location.href = \"home.jsp\"");
            out.println("</script>");
         }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
