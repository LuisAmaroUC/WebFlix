package servlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import auxPack.managerServices;
import auxPack.userServices;
import org.apache.log4j.Logger;

@WebServlet("/ServletEditUser")
public class ServletEditUser extends HttpServlet {
    static Logger logger = Logger.getLogger(ServletEditUser.class);
    @EJB
    userServices ejb;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            if ((boolean) request.getSession().getAttribute("loggedIn")) {

              //  logger.info("Name:" + request.getParameter("name"));
              //  logger.info("ccNumber:" + Integer.parseInt(request.getParameter("ccNumber")));

                    boolean flag = ejb.edit( request.getSession().getAttribute("email").toString(),request.getParameter("name"), Integer.parseInt(request.getParameter("ccNumber")));

                    if (flag) response.sendRedirect("menu.jsp");
                    else response.sendRedirect("errorEdit.jsp");


            }
            response.setContentType("text/html");
            out.println("<home><body><center>");



            out.println("<br><a href=\"menu.jsp\">Voltar ao Menu</a>");
            out.println("<br><a href=\"ServletLogout\">Logout</a>");
        }catch(Exception e){

            out.println("<script type=\"text/javascript\">");
            out.println("alert('ccNumber é um inteiro');");
            out.println("window.location.href = \"editarUser.jsp\"");
            out.println("</script>");
        }
    }
}
