package servlet;

import auxPack.GenerateMovies;
import auxPack.superManagerServices;
import auxPack.userServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class indexServlet
 *
 *
 *
   LEARN WRITE CLEANER CODE
 1- DESCRIPTIVE NAMES
 2- GIVE EACH CLASS/FUNCTION ONE PURPOSE
 3- DELETE UNNECESSARY CODE
 4-READABILITY > CLEVERNESS
 5-KEEP A CONSISTENT CODING STYLE
 6- CHOSE THE RIGHT ARCHITECTURE
 7- MASTER THE LANGUAGE IDIOMS
 8- STUDY THE CODE OF MASTERS
 9- WRITE GOOD COMMENTS
 10- THERE´S ALWAYS SOMETHING NEW TO








 */
@WebServlet("/IndexS")
public class indexServlet extends HttpServlet {
    int runOneTime = 0;
    private static final long serialVersionUID = 1L;
    HttpSession session = null;

    @EJB
    GenerateMovies create;
    @EJB
    superManagerServices createManagers;
    @EJB
    userServices pay;
    private void generateMovies() throws IOException {
        File file = new File("C:\\Users\\hp\\Desktop\\project2-code-parent\\project2-code-web\\src\\main\\java\\servlet\\generateMovies.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        String [] parts;
        while ((st = br.readLine()) != null){
            parts = st.split(" ");

            create.generateMovies(parts[0],Double.parseDouble(parts[1]),parts[2],parts[3],Integer.parseInt(parts[4]));
        }


    }
    private void generateManagers() throws IOException {
        File file = new File("C:\\Users\\hp\\Desktop\\project2-code-parent\\project2-code-web\\src\\main\\java\\servlet\\generateManagers.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        String [] parts;
        while ((st = br.readLine()) != null){
            parts = st.split(" ");

            createManagers.create(parts[0],parts[1],parts[2],Integer.parseInt(parts[3]));

        }


    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        session.setAttribute("loggedIn", false);

       if(runOneTime == 0) {
          // pay.payment();
           generateMovies();
           generateManagers();
           runOneTime = 1;
       }
        response.sendRedirect("home.jsp");

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
