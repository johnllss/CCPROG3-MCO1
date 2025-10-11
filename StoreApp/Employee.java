package StoreApp;

public class Employee extends User {
    private String role;

    public Employee()
    {
        super();
        this.role = "N/A";
    }

    public Employee(String uniqueID, String name, String email, String password, String role) {
        super(uniqueID, name, email, password);
        this.role = role;
    }

    public String getRole()
    {
        return this.role;
    }

    public void setRole(String newRole)
    {
        this.role = newRole;
    }
}
