package servlet;

import DataTransferObjects.movieDTO;
import auxPack.userServices;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ShowUserMovies")
public class ServletShowUserMovies extends HttpServlet {

    @EJB
    userServices watchList;
    @EJB
    userServices check;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        if((boolean)request.getSession().getAttribute("loggedIn")){

            List<movieDTO> movies = watchList.getWatchList(request.getSession().getAttribute("email").toString());
            boolean flag = check.checkType(request.getSession().getAttribute("email").toString());
            response.setContentType("text/html");

            if(movies.size() >0) {
                String lastType = movies.get(0).getType();

                out.println("<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <title>Bootstrap Example</title>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                        "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
                        "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
                        "</head>\n" +
                        "<body><center>");
                out.println("<div class=\"container\">\n" +
                        "  <h2>Watch List</h2>");

                out.println("<h3><p class=\"bg-primary\">"+lastType + "<br>"+" </p></h3>");
                for (movieDTO movie : movies) {
                    if (movie.getType().equals(lastType)) {
                        out.println("<p class=\"bg-info\">"+movie.getId() + "\t"+ movie.getTitle() + "<br>"+" </p>");

                    } else {
                        lastType = movie.getType();
                        out.println("<h3><p class=\"bg-primary\">"+lastType + "<br>"+" </p></h3>");
                        out.println("<p class=\"bg-info\">"+movie.getId() + "\t" + movie.getTitle() + "<br>"+" </p>");
                    }
                }
                out.println("<form id=\"1\" action=\"removeMovies\" method=\"POST\">" +
                        "<input type=\"text\" name=\"id\"/>"
                        + "<input type=\"submit\" class=\"btn btn-dark\" value=\"Remover\"/>"
                        + "</form>");
            }
            else {
                out.println("<home><body><center>");
            }
            if(flag) out.println("<br><a href=\"menuManagers.jsp\">Voltar ao Menu</a>");
            else out.println("<br><a href=\"menu.jsp\">Voltar ao Menu</a>");
            out.println("<br><a href=\"ServletLogout\">Logout</a>");

            out.println("</div>\n" +
                    "\n" +
                    "</center></body>\n" +
                    "</html>\n");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
