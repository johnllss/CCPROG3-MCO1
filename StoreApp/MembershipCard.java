package StoreApp;

public class MembershipCard {
    private String cardNumber;
    private int points;

    /**
     * Class MembershipCard parameterized constructor
     * @param cardNumber is the membership card number of the Customer
     */
    public MembershipCard(String cardNumber) {
        this.cardNumber = cardNumber;
        this.points = 0;
    }

    /**
     * This method adds points to the customer's membership points.
     * @param points is the number of points to add.
     * @return boolean for success/failure.
     */
    public boolean addPoints(int points) {
        this.points += points;
        return true;
    }

    /**
     * This method deducts points from the customer after redeeming.
     * @param points is the number of points to deduct.
     * @return boolean for success/failure.
     */
    public boolean redeemPoints(int points) {
        if (points >= this.points) {
            return false;
        }
        else  {
            this.points -= points;
            return true;
        }
    }

    /**
     * This method generates the card number of the customer.
     * @return String for the generatedCardNumber.
     */
    public String generateCardNumber()
    {
        // TODO generation

        return "";
    }
    
    /**
     * This is a getter method to get the cardNumber.
     * @return String for the cardNumber.
     */
    public String getCardNumber() {
        return cardNumber;
    }


    /**
     * This is a getter method to get the points of the customer.
     * @return int for the points.
     */
    public int getPoints() {
        return points;
    }

    public void setCardNumber(String newCardNumber)
    {
        this.cardNumber = newCardNumber;
    }
}
