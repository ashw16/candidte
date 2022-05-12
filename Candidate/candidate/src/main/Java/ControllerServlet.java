import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
 @WebServlet("/")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CandidateDAO candidateDAO;
 
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/candidate1";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        candidateDAO = new CandidateDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertCandidate(request, response);
                break;
            case "/delete":
                deleteCandidate(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateCandidate(request, response);
                break;
                case "/dept" :
                showNewDept(request, response);
                break;
                case "/deptdept" :
                listCandidateDept(request, response);
                break;
            default:
                listCandidate(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listCandidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Candidate> listCandidate = candidateDAO.listAllCandidate();
        request.setAttribute("listCandidate", listCandidate);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CandidateList.jsp");
        dispatcher.forward(request, response);
    }



    //edit start
    private void listCandidateDept(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    List<Candidate> listCandidate = candidateDAO.listAllCandidateDept();
    request.setAttribute("listCandidate", listCandidate);
    RequestDispatcher dispatcher = request.getRequestDispatcher("ListDept.jsp");
    dispatcher.forward(request, response);
    }

    private void showNewDept(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("Deptwise.jsp");
    dispatcher.forward(request, response);
}
    //edit end
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CandidateForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Candidate existingCandidate = candidateDAO.getCandidate(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CandidateForm.jsp");
        request.setAttribute("candidate", existingCandidate);
        dispatcher.forward(request, response);
 
    }
 
    private void insertCandidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email= request.getParameter("email");
        // String dept = request.getParameter("dept");
        int dept = Integer.parseInt(request.getParameter("dept"));
    
        // int dept_id=50;
        // if(dept=="HR")
        // dept_id=10;
        // else if(dept=="SALES")
        // dept_id=20;
        // else if(dept=="ADMIN")
        // dept_id=30;
        // else if(dept=="MANAGING")
        // dept_id=40;
        // switch(dept)
        // {
        //     case "HR" : dept_id=10; break;
        //     case "SALES": dept_id=20;break;
        //     case "ADMIN": dept_id=30; break;
        //     case "MANAGING": dept_id=40; break;
        // }

 
        Candidate newCandidate = new Candidate(name, email, dept);
        candidateDAO.insertCandidate(newCandidate);
        response.sendRedirect("list");
    }
 
    private void updateCandidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        int dept =Integer.parseInt(request.getParameter("dept"));
    
        // switch(dept)
        // {
        //     case "HR" : dept_id=10; break;
        //     case "SALES": dept_id=20;break;
        //     case "ADMIN": dept_id=30; break;
        //     case "MANAGING": dept_id=40; break;
        // }
 
        Candidate candidate = new Candidate(id, name, email, dept);
        candidateDAO.updateCandidate(candidate);
        response.sendRedirect("list");
    }
 
    private void deleteCandidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Candidate candidate = new Candidate(id);
        candidateDAO.deleteCandidate(candidate);
        response.sendRedirect("list");
 
    }
}