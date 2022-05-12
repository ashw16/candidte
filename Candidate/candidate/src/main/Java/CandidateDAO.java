import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
/**
 * CandidateDAO.java
 * This DAO class provides CRUD database operations for the table Student
 * in the database.
 * @author www.codejava.net
 *
 */
public class CandidateDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public CandidateDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertCandidate(Candidate candidate) throws SQLException {
        String sql = "INSERT INTO candidate (name, email, dept_id) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, candidate.getName());
        statement.setString(2, candidate.getEmail());
        statement.setInt(3, candidate.getDept_id());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Candidate> listAllCandidate() throws SQLException {
        List<Candidate> listCandidate = new ArrayList<Candidate>();
         
        String sql = "SELECT * FROM candidate";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("candidate_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int dept_id = resultSet.getInt("dept_id");
             
            Candidate candidate = new Candidate(id, name, email, dept_id);
            listCandidate.add(candidate);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listCandidate;
    }

    //edit start
    public List<Candidate> listAllCandidateDept() throws SQLException {
        List<Candidate> listCandidate = new ArrayList<Candidate>();
         
        // String sql = "SELECT * FROM candidate";
        String sql =  "SELECT candidate_id,name, email FROM candidate INNER JOIN department1 ON candidate.dept_id=department1.dept_id where dept_name='SALES'";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("candidate_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            // String dept_name = resultSet.getString("dept_name");
             
            Candidate candidate = new Candidate(id, name, email);
            listCandidate.add(candidate);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listCandidate;
    }
    //edit end
     
    public boolean deleteCandidate(Candidate candidate) throws SQLException {
        String sql = "DELETE FROM candidate where candidate_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, candidate.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateCandidate(Candidate candidate) throws SQLException {
    
        String sql = "UPDATE candidate SET name = ?, email = ?, dept_id = ?";
        sql += " WHERE candidate_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, candidate.getName());
        statement.setString(2, candidate.getEmail());
        statement.setInt(3, candidate.getDept_id());
        statement.setInt(4, candidate.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Candidate getCandidate(int id) throws SQLException {
        Candidate candidate= null;
        String sql = "SELECT * FROM candidate WHERE candidate_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int dept_id = resultSet.getInt("dept_id");
             
            candidate = new Candidate(id, name, email, dept_id);
        }
         
        resultSet.close();
        statement.close();
         
        return candidate;
    }
}