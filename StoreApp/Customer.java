package StoreApp;
import java.io.File;
import java.io.IOexecption;

public class Customer extends User {
    private MembershipCard membershipCard;
    private boolean isSenior;
    private Cart cart;

    public Customer(String name, String email, String password, MembershipCard membershipCard, boolean isSenior) {
        super(name, email, password);
        this.membershipCard = membershipCard;
        this.isSenior = isSenior;
        this.cart = new Cart();
    }

    /***
     *
     * @return
     */
    public boolean hasMembership()
    {
        if (membershipCard == null)
        {
            return false;
        }

        return true;
    }

    /**
     *
     * @return
     */
    public boolean isSenior()
    {
        return this.isSenior;
    }

    public boolean saveCustomerDataToFile()
    {
        // TODO implement
        return false;
    }

    public boolean loadCustomerDataFromFile()
    {
        // TODO implement
        return false;
    }

    // Getters and setters
    /**
     *
     * @return
     */
    public MembershipCard getMembershipCard()
    {
        return membershipCard;
    }

    public Cart getCart() 
    {
        return cart;
    }
}
