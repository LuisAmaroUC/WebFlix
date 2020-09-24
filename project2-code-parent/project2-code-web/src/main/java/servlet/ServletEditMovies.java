package servlet;

import DataTransferObjects.movieDTO;
import auxPack.movieS;
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

@WebServlet("/editarMovie")
public class ServletEditMovies extends HttpServlet {
    @EJB
    movieS movie;
    @EJB
    userServices check;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        boolean checkManager = check.checkType(request.getSession().getAttribute("email").toString());
        if((boolean)request.getSession().getAttribute("loggedIn")){
            if(checkManager){
                List<movieDTO> movies = movie.getAll();
                response.setContentType("text/html");

                if(movies.size() > 0) {
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
                            "  <h2>Edit Movies</h2>");


                    out.println("<h3><p class=\"bg-primary\">"+lastType + "<br>"+" </p></h3>");
                    for (movieDTO movie : movies) {
                        if (movie.getType().equals(lastType)) {
                            out.println("<p class=\"bg-info\">"+movie.getId() + "\t"+ movie.getTitle() +"\t" + movie.getLength()+ "\t"+movie.getDirector()+"\t"+movie.getYear() + "<br>"+" </p>");

                        } else {
                            lastType = movie.getType();
                            out.println("<h3><p class=\"bg-primary\">"+lastType + "<br>"+" </p></h3>");
                            out.println("<p class=\"bg-info\">"+movie.getId() + "\t"+ movie.getTitle() +"\t" +movie.getLength()+ "\t"+movie.getDirector()+"\t"+movie.getYear() + "<br>"+" </p>");

                        }
                    }

                    out.println("<form id=\"1\" action=\"editarFilme\" method=\"POST\">\n" +
                            "    <table border=\"1\" align=\"center\">\n" +
                            "        <tr>\n" +
                            "            <td> Title :</td>\n" +
                            "            <td><input type=\"text\" name=\"title\" value=" + "\"default\"/></td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td> length :</td>\n" +
                            "            <td><input type=\"text\" name=\"length\" value=\"0\"/></td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td> type :</td>\n" +
                            "            <td><input type=\"text\" name=\"type\" value=\"default\"/></td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td> director :</td>\n" +
                            "            <td><input type=\"text\" name=\"director\" value=\"default\"/></td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td> year :</td>\n" +
                            "            <td><input type=\"text\" name=\"year\" value=\"0\"/></td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" + "<input type=\"text\" name=\"id\"/>"
                            + "<input type=\"submit\" value=\"Adicionar\"/>" +
                            "    </table>\n" +
                            "</form>");
                }else{
                    out.println("<home><body><center>");
                }
                out.println("<br><a href=\"menuManagers.jsp\">Voltar ao Menu</a>");
                out.println("<br><a href=\"ServletLogout\">Logout</a>");
            }else{
                response.sendRedirect("notManager.jsp");
            }

        }else{
            response.sendRedirect("notLoggedIn.jsp");

        }

        out.println("</div>\n" +
                "\n" +
                "</center></body>\n" +
                "</html>\n");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
