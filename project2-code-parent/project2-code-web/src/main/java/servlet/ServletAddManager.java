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

@WebServlet("/addManager")
public class ServletAddManager extends HttpServlet {

    @EJB
    managerServices create;
    @EJB
    managerServices check;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        boolean checkType = check.checkManagerType(request.getSession().getAttribute("email").toString());

            if ((boolean) request.getSession().getAttribute("loggedIn")) {

                if(checkType){

                    try {
                        boolean flag = create.createManager(request.getParameter("email"), request.getParameter("name"),request.getParameter("password"),  (request.getParameter("ccNumber")));
                        if (flag) {
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Manager criado com sucesso!');");
                            out.println("window.location.href = \"addManager.jsp\"");
                            out.println("</script>");
                        } else{
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('User já existe!');");
                            out.println("window.location.href = \"addManager.jsp\"");
                            out.println("</script>");
                        }
                    }catch (Exception e){
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('ccNumber é um inteiro!');");
                        out.println("window.location.href = \"addManager.jsp\"");
                        out.println("</script>");
                    }
               }else {

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Não tem permissões para estar aqui!');");
                    out.println("window.location.href = \"menu.jsp\"");
                    out.println("</script>");
                }
            } else{

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Sessão não iniciada!');");
                out.println("window.location.href = \"handleManagers.jsp\"");
                out.println("</script>");
            }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
