package StoreApp;
/**
 *
 */
public class User {
    private int uniqueID;
    private static int uniqueIDCounter = 0;
    private String name;
    private String email;
    private String password;

    public User() 
    {
        this.uniqueID = ++uniqueIDCounter; // pre-increment so first instance is int 1
        this.name = "N/A";
        this.email = "N/A";
        this.password = "N/A";
    }

    public User(String name, String email, String password)
    {
        this.uniqueID = ++uniqueIDCounter;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean login(String email, String password)
    {
        if(email.equals(this.email) && password.equals(this.password))
        {
            return true;
        }

        return false;
    }

    // public boolean logout()
    // {

    // }

    // Getters and Setters
    public String getUniqueID()
    {
        return uniqueID;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String name)
    {
        this.name = name;
    }

    public void setUniqueID(String id)
    {
        this.uniqueID = id;
    }
}

