package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import auxPack.userServices;
import org.apache.log4j.Logger;

@WebServlet("/CreateUser")
public class createUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(createUserServlet.class);
    Date dNow = new Date( );
    //SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

    @EJB
    userServices create;
    @EJB
    userServices pay;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {

                boolean flag = create.create(request.getParameter("email"), request.getParameter("name"),request.getParameter("password"),  Integer.parseInt(request.getParameter("ccNumber")),dNow);
                //pay.payment();
                if (flag) {
                    request.getSession().setAttribute("email", request.getParameter("email"));
                    request.getSession().setAttribute("loggedIn", true);
                    response.sendRedirect("menu.jsp");
                } else {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Utilizador Já Existe Insira outro email');");
                    out.println("window.location.href = \"createUser.jsp\"");
                    out.println("</script>");

                }
            }catch (Exception e){
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Input Incorreto! O credit card number é um inteiro');");
                out.println("window.location.href = \"createUser.jsp\"");
                out.println("</script>");
            }



    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
