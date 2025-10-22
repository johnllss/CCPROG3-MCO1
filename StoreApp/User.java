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
        // pre-increment so first instance is int 1
        this.uniqueID = ++uniqueIDCounter;
        
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

    // Getters and Setters
    public int getUniqueID()
    {
        return uniqueID;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword(){
        return password;
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

    public void setUniqueID(int id)
    {
        this.uniqueID = id;
    }
}

