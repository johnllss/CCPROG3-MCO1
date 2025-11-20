package StoreApp.Models;

public class Customer_Model extends User_Model {
    private MembershipCard membershipCard;
    private boolean isSenior;
    private int age;
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
    public Customer_Model(String name, String email, String password, MembershipCard membershipCard, boolean isSenior, Cart_Model cart) {
        super(name, email, password);
        this.membershipCard = membershipCard;
        this.isSenior = isSenior;
        this.cart = cart;
    }


    public MembershipCard getMembershipCard() {
        return membershipCard;
    }
    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }
    public boolean isSenior() {
        return isSenior;
    }
    public void setSenior(boolean senior) {
        isSenior = senior;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Cart_Model getCart() {
        return cart;
    }
}
