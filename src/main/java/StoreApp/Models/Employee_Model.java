package StoreApp.Models;

public class Employee_Model extends User {
    private String role;
    public Employee_Model(String name, String email, String password, String role)
    {
        super(name, email, password);
        this.role = "N/A";
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
