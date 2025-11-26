package StoreApp.Models;

public class Customer_Model extends User_Model {
    private MembershipCard_Model membershipCard;
    private boolean isSenior;
    private Cart_Model cart;

    /***
     * Class Customer parameterized constructor
     * @param name provided name of customer
     * @param email provided email of customer
     * @param password created password of customer
     * @param membershipCard whether customer has membershipCard
     * @param isSenior whether customer is a senior
     * @param cart object that customer used during whole shopping
     */
    public Customer_Model(String name, String email, String password, MembershipCard_Model membershipCard, boolean isSenior, Cart_Model cart) {
        super(name, email, password);
        this.membershipCard = membershipCard;
        this.isSenior = isSenior;
        this.cart = cart;
    }

    /***
     * method that checks if the saved customer has a membership card
     * @return boolean has membership = true null membership = false
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
     * This is a getter method to get Customer's senior status.
     * @return boolean for isSenior.
     */
    public boolean isSenior() {
        return isSenior;
    }

    /**
     * This is a getter method to get Customer's membership card.
     * @return MembershipCard_Model for membershipCard.
     */
    public MembershipCard_Model getMembershipCard() {
        return membershipCard;
    }

    /**
     * This is a getter method to get Customer's cart.
     * @return Cart_Model for cart.
     */
    public Cart_Model getCart() {
        return cart;
    }

    /**
     * This sets Customer's membership card.
     * @param membershipCard is the membership card to be assigned.
     */
    public void setMembershipCard(MembershipCard_Model membershipCard) {
        this.membershipCard = membershipCard;
    }

    /**
     * This sets Customer's senior status.
     * @param senior is the senior status to be set.
     */
    public void setSenior(boolean senior) {
        isSenior = senior;
    }
}
