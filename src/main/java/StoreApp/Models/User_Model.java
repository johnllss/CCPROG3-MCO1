package StoreApp.Models;

public class User_Model {
    private int uniqueID;
    private static int uniqueIDCounter = 0;
    private String name;
    private String email;
    private String password;

    /**
     * Class User default constructor
     */
    public User_Model()
    {
        // pre-increment so first instance is int 1
        this.uniqueID = ++uniqueIDCounter;

        this.name = "N/A";
        this.email = "N/A";
        this.password = "N/A";
    }

    public User_Model(String name, String email, String password){
        this.uniqueID = ++uniqueIDCounter;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getUniqueID() {
        return uniqueID;
    }
    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
