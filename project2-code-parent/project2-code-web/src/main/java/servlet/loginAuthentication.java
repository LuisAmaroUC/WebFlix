package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auxPack.loginServices;
import auxPack.userServices;


@WebServlet("/Login")
public class loginAuthentication extends HttpServlet {



    @EJB
    loginServices login;
    @EJB
    userServices check;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean isManager = check.checkType(request.getParameter("email"));
        boolean flag =  login.login(request.getParameter("email"), request.getParameter("password"));


        if(flag ) {
            request.getSession().setAttribute("email", request.getParameter("email"));
            request.getSession().setAttribute("loggedIn", true);

            if(isManager) response.sendRedirect("menuManagers.jsp");
            else response.sendRedirect("menu.jsp");

        }
        else {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('LOGIN INVÁLIDO');");
            out.println("window.location.href = \"home.jsp\"");
            out.println("</script>");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
