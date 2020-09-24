package servlet;

import DataTransferObjects.managerDTO;
import DataTransferObjects.personDTO;
import auxPack.managerServices;
import auxPack.superManagerServices;
import auxPack.userServices;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/removeManager")
public class ServletRemoveManager extends HttpServlet {

    @EJB
    managerServices get;
    @EJB
    managerServices check;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        boolean checkType = check.checkManagerType(request.getSession().getAttribute("email").toString());
        if((boolean)request.getSession().getAttribute("loggedIn")){

            if(checkType){

               List<personDTO> users = get.getAllManagers();

               if (users.size() > 0) {
                   response.setContentType("text/html");
                   out.println("<home><body><center>");

                   for (personDTO user : users) {
                       out.println(user.getName() + "-->" + user.getEmail() + "<br>");
                   }
                   out.println("<form id=\"1\" action=\"removeManag\" method=\"POST\">" +
                           "<input type=\"text\" name=\"email\"/>"
                           + "<input type=\"submit\" value=\"Remover\"/>"
                           + "</form>");
               } else {
                   response.setContentType("text/html");
                   out.println("<home><body><center>");
               }
               out.println("<br><a href=\"menuSuperManager.jsp\">Voltar ao Menu</a>");
               out.println("<br><a href=\"ServletLogout\">Logout</a>");
           }else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Not allowed in here');");
                out.println("window.location.href = \"home.jsp\"");
                out.println("</script>");
            }

        }else{
            out.println("<script type=\"text/javascript\">");
            out.println("alert('You´re not loggedIn!');");
            out.println("window.location.href = \"handleManagers.jsp\"");
            out.println("</script>");;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
