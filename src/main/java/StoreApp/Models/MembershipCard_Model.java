package StoreApp.Models;

import java.time.LocalDate;

/***
 * Represents users membership card, should there be a presence of it
 */
public class MembershipCard_Model {
    private String cardNumber;
    private int points;
    private LocalDate registrationDate;

    /**
     * Class MembershipCard parameterized constructor
     * @param cardNumber is the membership card number of the Customer
     * @param registrationDate is the date the membership was created
     */
    public MembershipCard_Model(String cardNumber, LocalDate registrationDate) {
        this.cardNumber = cardNumber;
        this.points = 0;
        this.registrationDate = registrationDate;
    }

    /**
     * Class MembershipCard parameterized constructor (overload for backward compatibility)
     * @param cardNumber is the membership card number of the Customer
     */
    public MembershipCard_Model(String cardNumber) {
        this(cardNumber, LocalDate.now());
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

    /**
     * This sets a new Card number for the membership card
     * @param newCardNumber desired string of numbers that would replace current
     */
    public void setCardNumber(String newCardNumber)
    {
        this.cardNumber = newCardNumber;
    }

    /**
     * This sets the points for the membership card
     * @param points the number of points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * This is a getter method to get the registration date.
     * @return LocalDate for the registrationDate.
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
