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

@WebServlet("/ServletDeleteUser")
public class ServletDeleteUser extends HttpServlet {
    @EJB
    userServices ejb;
    @EJB
    userServices check;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean checkType = check.checkType(request.getSession().getAttribute("email").toString());
        if((boolean)request.getSession().getAttribute("loggedIn")) {
            if (request.getParameter("Option").equals("noButton")) {

              if(checkType)  response.sendRedirect("menuManagers.jsp");
              else response.sendRedirect("menu.jsp");
            }
            if (request.getParameter("Option").equals("yesButton")) {
                boolean flag = ejb.deleteUser(request.getSession().getAttribute("email").toString());
                if (flag) {
                    request.getSession().setAttribute("loggedIn", false);


                    response.sendRedirect("home.jsp");

                } else response.sendRedirect("error.jsp");
            }
        }
    }
}
