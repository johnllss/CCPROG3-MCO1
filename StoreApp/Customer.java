package StoreApp;

public class Customer extends User {
    private MembershipCard membershipCard;
    private boolean isSenior;
    private Cart cart;

    public Customer(String name, String email, String password, MembershipCard membershipCard, int uniqueID,boolean isSenior) {
        super(name, email, password, uniqueID);
        this.membershipCard = membershipCard;
        this.isSenior = isSenior;
        this.cart = new Cart();
    }

    // public boolean hasMembership()
    // {

    // }

    /**
     *
     * @return
     */
    public boolean isSenior()
    {
        return this.isSenior;
    }

    // public boolean saveCustomerDataToFile()
    // {

    // }

    // public loadCustomerDataFromFile()
    // {

    // }

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
