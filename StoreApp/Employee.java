package StoreApp;

public class Employee extends User {
    private String role;

    public Employee()
    {
        super();
        this.role = "N/A";
    }

    public Employee(String name, String email, String password, String role) {
        super(name, email, password);
        this.role = role;
    }
    public boolean login(String email, String password)
    {
        if(email.equals(this.getEmail()) && password.equals(this.getPassword()))
        {
            return true;
        }

        return false;
    }

    //To-do//
    public void restock(Inventory inventory,int id, int quantity)
    {

        inventory.restockProduct(id, quantity);
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String newRole)
    {
        this.role = newRole;
    }
}
