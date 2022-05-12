public class Candidate {
    protected int id;
    protected String name;
    protected String email;
    // protected String dept;
    protected int dept_id;
 
    public Candidate() {
    }
 
    public Candidate(int id) {
        this.id = id;
    }
 
    public Candidate(int id, String name, String email, int dept_id) {
        this(name, email, dept_id);
        this.id = id;
    }
    public Candidate(String name, String email, int dept_id) {
        this.name = name;
        this.email = email;
        this.dept_id = dept_id;
    }
    public Candidate(int id, String name, String email) {
        this.name = name;
        this.email = email;
        this.id=id;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public int getDept_id() {
        return dept_id;
    }
 
    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }
}